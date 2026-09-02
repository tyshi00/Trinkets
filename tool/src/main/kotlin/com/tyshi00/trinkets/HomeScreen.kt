package com.tyshi00.trinkets

import androidx.compose.foundation.background
import com.thelightphone.sdk.ui.lightClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.InitialScreen
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.buildDatabase
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightIcons
import com.thelightphone.sdk.ui.LightSurfaceScheme
import com.thelightphone.sdk.ui.LightText
import com.thelightphone.sdk.ui.LightTextVariant
import com.thelightphone.sdk.ui.LightTheme
import com.thelightphone.sdk.ui.LightThemeController
import com.thelightphone.sdk.ui.LightThemeTokens
import com.thelightphone.sdk.ui.LightTopBar
import com.thelightphone.sdk.ui.LightTopBarCenter
import com.thelightphone.sdk.ui.gridUnitsAsDp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeState(
    val homeDefault: HomeDefault = HomeDefault.COUNTDOWN,
    val countdowns: List<CountdownDisplayItem> = emptyList(),
    val poem: Poem? = null,
    val excerpt: Excerpt? = null,
    val philosophy: PhilosophyPrompt? = null,
    val morning: MorningPrompt? = null,
    val joke: Joke? = null,
    val trivia: TriviaItem? = null,
    val historyFact: HistoryFact? = null,
    val motivationIntensity: MotivationIntensity? = null,
    val dateFormat: DateFormat = DateFormat.MDY,
    val countdownTimerEnabled: Boolean = false,
    val splitHomeEnabled: Boolean = false,
    val splitPrimary: SplitSlot = SplitSlot.COUNTDOWN,
    val splitSecondary: SplitSlot = SplitSlot.MORNING,
    val featuredCountdownId: Long? = null,
    val reflection: ReflectionPrompt? = null,
) {
    /**
     * The countdown to show when a Home slot displays Countdowns: the starred
     * one if it still exists, otherwise the soonest upcoming (the list is
     * already sorted by days remaining).
     */
    val featuredCountdown: CountdownDisplayItem?
        get() = countdowns.firstOrNull { it.id == featuredCountdownId } ?: countdowns.firstOrNull()
}

class HomeViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    override fun onScreenShow(screen: SimpleLightScreen<Unit>) {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            val invertColors = repo.getInvertColors()
            if (invertColors) LightThemeController.setLightTheme() else LightThemeController.setDarkTheme()

            val visibility = repo.getFeatureVisibility()
            var homeDefault = repo.getHomeDefault()

            // If the feature currently chosen as Home default has been
            // turned off, fall back to Countdowns instead of showing a
            // screen for a feature the person just disabled.
            val defaultStillVisible = when (homeDefault) {
                HomeDefault.COUNTDOWN -> true
                HomeDefault.POEM -> visibility.poemEnabled
                HomeDefault.EXCERPT -> visibility.excerptEnabled
                HomeDefault.HISTORY -> visibility.historyEnabled
                HomeDefault.PHILOSOPHY -> visibility.philosophyEnabled
                HomeDefault.MORNING -> visibility.morningEnabled
                HomeDefault.JOKE -> visibility.jokeEnabled
                HomeDefault.TRIVIA -> visibility.triviaEnabled
                HomeDefault.REFLECTION -> visibility.reflectionEnabled
            }
            if (!defaultStillVisible) homeDefault = HomeDefault.COUNTDOWN

            val dateFormat = repo.getDateFormat()
            val countdownTimerEnabled = repo.getCountdownTimerEnabled()

            val countdowns = repo.getCountdowns().map {
                CountdownDisplayItem(
                    id = it.id,
                    name = it.name,
                    date = it.date,
                    dateDisplay = dateLabel(it.date, dateFormat),
                    countdownDisplay = countdownLabel(it.date),
                    notes = it.notes,
                )
            }.sortedBy { daysUntil(it.date) }

            val intensity = repo.getMotivationIntensity()

            // Same guard as homeDefault: if a feature chosen for a split slot
            // has since been switched off in Settings, fall back to Countdowns
            // rather than render an empty half.
            fun slotStillVisible(slot: SplitSlot): Boolean {
                val feature = slot.toFeature() ?: return true // Countdowns is always available
                return visibility.isEnabled(feature)
            }
            val splitPrimary = repo.getSplitPrimary().let { if (slotStillVisible(it)) it else SplitSlot.COUNTDOWN }
            val splitSecondary = repo.getSplitSecondary().let { if (slotStillVisible(it)) it else SplitSlot.COUNTDOWN }

            _state.value = HomeState(
                homeDefault = homeDefault,
                countdowns = countdowns,
                poem = ContentRepository.poemOfTheDay(),
                excerpt = ContentRepository.excerptOfTheDay(),
                philosophy = ContentRepository.philosophyPromptOfTheDay(),
                morning = ContentRepository.morningPromptOfTheDay(intensity),
                joke = ContentRepository.jokeOfTheDay(),
                trivia = ContentRepository.triviaOfTheDay(),
                historyFact = ContentRepository.historyFactOfTheDay(),
                motivationIntensity = intensity,
                dateFormat = dateFormat,
                countdownTimerEnabled = countdownTimerEnabled,
                splitHomeEnabled = repo.getSplitHomeEnabled(),
                splitPrimary = splitPrimary,
                splitSecondary = splitSecondary,
                featuredCountdownId = repo.getFeaturedCountdownId(),
                reflection = ContentRepository
                    .reflectionPromptsForToday(repo.getUsedReflectionIds())
                    .firstOrNull(),
            )
        }
    }
}

@InitialScreen
class HomeScreen(sealedActivity: SealedLightActivity) : LightScreen<Unit, HomeViewModel>(sealedActivity) {

    private val repo by lazy {
        TrinketsRepository.getInstance {
            lightContext.buildDatabase(TrinketsDatabase::class.java, "trinkets.db")
        }
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun createViewModel() = HomeViewModel(repo)

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        val state by viewModel.state.collectAsState()

        LightTheme(colors = themeColors) {
            val isDark = LightThemeTokens.surfaceScheme == LightSurfaceScheme.Dark
            val calendarIconRes = if (isDark) R.drawable.ic_calendar_white else R.drawable.ic_calendar_black

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightThemeTokens.colors.background),
            ) {
                LightTopBar(
                    center = LightTopBarCenter.Text("Trinkets"),
                    modifier = Modifier.padding(bottom = 0.5f.gridUnitsAsDp()),
                )

                val openSlot: (SplitSlot) -> Unit = { slot ->
                    val feature = slot.toFeature()
                    if (feature == null) {
                        navigateTo(screenFactory = { CountdownScreen(it, repo) })
                    } else {
                        navigateTo(
                            screenFactory = { openFeatureScreen(it, feature, state.motivationIntensity) },
                        )
                    }
                }

                if (state.splitHomeEnabled) {
                    // Two equal halves. Each is independently scrollable so a
                    // long poem in one doesn't push the other off screen, and
                    // tapping either opens that feature's own screen (including
                    // Joke/Trivia, which are too cramped to reveal in place).
                    //
                    // Content is centered within each half. Centering only
                    // works while the block fits: an overflowing child in a
                    // scrolling Column stops centering and clips instead, which
                    // is why the line caps in SlotContent matter. The split is
                    // slightly above even (46/54) so the divider sits a touch
                    // high, which reads better than a dead-centre rule and
                    // leaves the taller secondary block more room.
                    Column(
                        modifier = Modifier
                            .weight(0.46f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .lightClickable { openSlot(state.splitPrimary) }
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SlotContent(slot = state.splitPrimary, state = state, compact = false)
                    }

                    SplitDivider()

                    Column(
                        modifier = Modifier
                            .weight(0.54f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .lightClickable { openSlot(state.splitSecondary) }
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        SlotContent(slot = state.splitSecondary, state = state, compact = true)
                    }
                } else {
                    // Centered both on the whole screen and within its own text
                    // block; still scrolls if content (a long poem/excerpt) runs
                    // past the visible area.
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        HomeContent(
                            state = state,
                            onOpenDetail = {
                                when (state.homeDefault) {
                                    HomeDefault.COUNTDOWN -> navigateTo(screenFactory = { CountdownScreen(it, repo) })
                                    HomeDefault.JOKE, HomeDefault.TRIVIA -> Unit // revealed in place, no navigation
                                    else -> {
                                        val feature = homeDefaultToFeature(state.homeDefault)
                                        if (feature != null) {
                                            navigateTo(
                                                screenFactory = { openFeatureScreen(it, feature, state.motivationIntensity) },
                                            )
                                        }
                                    }
                                }
                            },
                        )
                    }
                }

                // Settings sits in the far left slot to match native LightOS
                // menus, so the order reads settings, list, countdowns.
                LightBottomBar(
                    items = listOf(
                        LightBarButton.LightIcon(
                            icon = LightIcons.SETTINGS,
                            contentDescription = "Settings",
                            onClick = { navigateTo(screenFactory = { SettingsScreen(it, repo) }) },
                        ),
                        LightBarButton.LightIcon(
                            icon = LightIcons.LIST,
                            contentDescription = "Features",
                            onClick = { navigateTo(screenFactory = { FeaturesListScreen(it, repo) }) },
                        ),
                        LightBarButton.Icon(
                            painter = painterResource(id = calendarIconRes),
                            contentDescription = "Countdowns",
                            onClick = { navigateTo(screenFactory = { CountdownScreen(it, repo) }) },
                        ),
                    ),
                )
            }
        }
    }
}

/** Null only for Countdown, which navigates to its own dedicated screen instead. */
private fun homeDefaultToFeature(homeDefault: HomeDefault): TrinketsFeature? = when (homeDefault) {
    HomeDefault.COUNTDOWN -> null
    HomeDefault.HISTORY -> TrinketsFeature.HISTORY
    HomeDefault.POEM -> TrinketsFeature.POEM
    HomeDefault.EXCERPT -> TrinketsFeature.EXCERPT
    HomeDefault.PHILOSOPHY -> TrinketsFeature.PHILOSOPHY
    HomeDefault.MORNING -> TrinketsFeature.MORNING
    HomeDefault.JOKE -> TrinketsFeature.JOKE
    HomeDefault.TRIVIA -> TrinketsFeature.TRIVIA
    HomeDefault.REFLECTION -> TrinketsFeature.REFLECTION
}

@Composable
private fun HomeContent(state: HomeState, onOpenDetail: () -> Unit) {
    when (state.homeDefault) {
        HomeDefault.COUNTDOWN -> Column(
            modifier = Modifier
                .fillMaxWidth()
                .lightClickable(onClick = onOpenDetail),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CountdownHomeBlock(state.countdowns, state.countdownTimerEnabled)
        }

        HomeDefault.JOKE -> RevealHomeBlock(
            label = "JOKE OF THE DAY",
            prompt = state.joke?.setup,
            answer = state.joke?.punchline,
            revealHint = "Tap to see the punchline",
        )

        HomeDefault.TRIVIA -> RevealHomeBlock(
            label = "TRIVIA",
            prompt = state.trivia?.question,
            answer = state.trivia?.answer,
            revealHint = "Tap to see the answer",
        )

        else -> Column(
            modifier = Modifier
                .fillMaxWidth()
                .lightClickable(onClick = onOpenDetail),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (state.homeDefault) {
                HomeDefault.POEM -> TextHomeBlock(
                    "POEM OF THE DAY",
                    state.poem?.title,
                    state.poem?.body,
                    hint = state.poem?.author?.let { "— $it" },
                    bodyAlign = TextAlign.Start,
                )
                HomeDefault.EXCERPT -> TextHomeBlock(
                    "LITERARY EXCERPT",
                    null,
                    state.excerpt?.let { "\u201C${it.quote}\u201D" },
                    hint = state.excerpt?.let { "${it.author}, ${it.work}" },
                )
                HomeDefault.PHILOSOPHY -> TextHomeBlock("PHILOSOPHY PROMPT", null, state.philosophy?.prompt)
                HomeDefault.MORNING -> TextHomeBlock(
                    "MORNING PROMPT",
                    state.morning?.intensity?.label,
                    state.morning?.text,
                )
                HomeDefault.HISTORY -> HistoryHomeBlock(state.historyFact)
                HomeDefault.REFLECTION -> TextHomeBlock(
                    "REFLECTION",
                    null,
                    state.reflection?.text,
                    hint = "Tap to open",
                )
                else -> Unit
            }
        }
    }
}

/**
 * Hairline rule between the two halves of the split Home screen. The SDK has no
 * divider component, so this follows how it draws the scrollbar: a thin filled
 * Box using the muted content color, which tracks the light/dark theme.
 *
 * Height is a literal 1.dp rather than grid units: one grid unit is about 9dp
 * here, so any sensible fraction of it lands below 1dp and can round away to
 * nothing on low-density screens.
 */
@Composable
private fun SplitDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            // Vertical padding sits outside the 1.dp line, so it reserves a gap
            // above and below. Each half centers its own content, and the
            // secondary half is pushed upward by its bottom padding, so without
            // this the label underneath ends up sitting right on the rule.
            .padding(horizontal = 3f.gridUnitsAsDp(), vertical = 1.25f.gridUnitsAsDp())
            .height(1.dp)
            .background(LightThemeTokens.colors.contentSecondary),
    )
}

/**
 * Renders one half of the split Home screen. [compact] tightens the layout for
 * the smaller secondary slot: fewer body lines, and only the featured
 * countdown rather than the whole list.
 */
@Composable
private fun SlotContent(slot: SplitSlot, state: HomeState, compact: Boolean) {
    // Both halves are equal now, so the compact slot can show real content.
    // These caps exist to stop a very long poem from running past its half,
    // not to squeeze text: truncating a trivia question mid-sentence reads
    // worse than letting it use the space it needs.
    val bodyLines = if (compact) 5 else 6
    when (slot) {
        SplitSlot.COUNTDOWN -> FeaturedCountdownBlock(
            item = state.featuredCountdown,
            timerEnabled = state.countdownTimerEnabled,
        )
        SplitSlot.POEM -> TextHomeBlock(
            "POEM OF THE DAY",
            state.poem?.title,
            state.poem?.body,
            hint = state.poem?.author?.let { "— $it" },
            maxBodyLines = bodyLines,
            bodyAlign = TextAlign.Start,
        )
        SplitSlot.EXCERPT -> TextHomeBlock(
            "LITERARY EXCERPT",
            null,
            state.excerpt?.let { "\u201C${it.quote}\u201D" },
            hint = state.excerpt?.let { "${it.author}, ${it.work}" },
            maxBodyLines = bodyLines,
        )
        SplitSlot.PHILOSOPHY -> TextHomeBlock(
            "PHILOSOPHY PROMPT",
            null,
            state.philosophy?.prompt,
            maxBodyLines = bodyLines,
        )
        SplitSlot.MORNING -> TextHomeBlock(
            "MORNING PROMPT",
            state.morning?.intensity?.label,
            state.morning?.text,
            maxBodyLines = bodyLines,
        )
        SplitSlot.HISTORY -> TextHomeBlock(
            "TODAY IN HISTORY",
            state.historyFact?.let { it.heading },
            state.historyFact?.event ?: "Nothing logged for today yet.",
            maxBodyLines = bodyLines,
        )
        // In split mode these show the setup only and open their own screen on
        // tap, rather than revealing inline the way they do on classic Home.
        SplitSlot.JOKE -> TextHomeBlock(
            "JOKE OF THE DAY",
            null,
            state.joke?.setup,
            hint = "Tap to see the punchline",
            maxBodyLines = bodyLines,
        )
        SplitSlot.REFLECTION -> TextHomeBlock(
            "REFLECTION",
            null,
            state.reflection?.text,
            maxBodyLines = bodyLines,
        )
        SplitSlot.TRIVIA -> TextHomeBlock(
            "TRIVIA",
            null,
            state.trivia?.question,
            hint = "Tap to see the answer",
            maxBodyLines = bodyLines,
        )
    }
}

/** A single countdown (the starred one, or the soonest) for a split Home slot. */
@Composable
private fun FeaturedCountdownBlock(item: CountdownDisplayItem?, timerEnabled: Boolean) {
    if (item == null) {
        LightText(
            text = "No countdowns yet",
            variant = LightTextVariant.Detail,
            lighten = true,
            align = TextAlign.Center,
        )
        return
    }
    LightText(
        text = item.name.uppercase(),
        variant = LightTextVariant.Detail,
        lighten = true,
        align = TextAlign.Center,
    )
    Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
    if (timerEnabled) {
        var timer by remember(item.date) { mutableStateOf(countdownTimerFor(item.date)) }
        LaunchedEffect(item.date) {
            while (true) {
                delay(1000)
                timer = countdownTimerFor(item.date)
            }
        }
        LightText(text = timer.shortLabel(), variant = LightTextVariant.Heading, align = TextAlign.Center)
    } else {
        LightText(text = item.countdownDisplay, variant = LightTextVariant.Heading, align = TextAlign.Center)
    }
    Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
    LightText(text = item.dateDisplay, variant = LightTextVariant.Fine, lighten = true, align = TextAlign.Center)
}

@Composable
private fun CountdownHomeBlock(countdowns: List<CountdownDisplayItem>, timerEnabled: Boolean) {
    if (countdowns.isEmpty()) {
        LightText(
            text = "No countdowns yet",
            variant = LightTextVariant.Detail,
            lighten = true,
            align = TextAlign.Center,
        )
        return
    }
    countdowns.forEachIndexed { index, item ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LightText(
                text = item.name.uppercase(),
                variant = LightTextVariant.Detail,
                lighten = true,
                align = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
            if (timerEnabled) {
                var timer by remember(item.date) {
                    mutableStateOf(countdownTimerFor(item.date))
                }
                LaunchedEffect(item.date) {
                    while (true) {
                        delay(1000)
                        timer = countdownTimerFor(item.date)
                    }
                }
                LightText(text = timer.shortLabel(), variant = LightTextVariant.Heading, align = TextAlign.Center)
            } else {
                LightText(text = item.countdownDisplay, variant = LightTextVariant.Heading, align = TextAlign.Center)
            }
            Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
            LightText(text = item.dateDisplay, variant = LightTextVariant.Fine, lighten = true, align = TextAlign.Center)
        }
        if (index != countdowns.lastIndex) {
            Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
        }
    }
}

@Composable
private fun HistoryHomeBlock(fact: HistoryFact?) {
    TextHomeBlock(
        label = "TODAY IN HISTORY",
        title = fact?.let { it.heading },
        body = fact?.event ?: "Nothing logged for today yet.",
    )
}

@Composable
private fun TextHomeBlock(
    label: String,
    title: String?,
    body: String?,
    hint: String? = null,
    maxBodyLines: Int = 6,
    bodyAlign: TextAlign = TextAlign.Center,
) {
    LightText(text = label, variant = LightTextVariant.Detail, lighten = true, align = TextAlign.Center)
    Spacer(modifier = Modifier.height(0.75f.gridUnitsAsDp()))
    if (!title.isNullOrBlank()) {
        LightText(text = title, variant = LightTextVariant.Subheading, align = TextAlign.Center)
        Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
    }
    LightText(
        text = body ?: "Nothing to show yet.",
        variant = LightTextVariant.Paragraph,
        maxLines = maxBodyLines,
        align = bodyAlign,
    )
    if (hint != null) {
        Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
        LightText(text = hint, variant = LightTextVariant.Fine, lighten = true, align = TextAlign.Center)
    }
}

/**
 * Joke/Trivia on Home: tapping reveals the answer right here, in place,
 * instead of navigating to a separate screen.
 */
@Composable
private fun RevealHomeBlock(label: String, prompt: String?, answer: String?, revealHint: String) {
    var revealed by remember(prompt) { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .lightClickable { revealed = true },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LightText(text = label, variant = LightTextVariant.Detail, lighten = true, align = TextAlign.Center)
        Spacer(modifier = Modifier.height(0.75f.gridUnitsAsDp()))
        LightText(
            text = prompt ?: "Nothing to show yet.",
            variant = LightTextVariant.Paragraph,
            maxLines = 6,
            align = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(0.75f.gridUnitsAsDp()))
        if (revealed) {
            LightText(
                text = answer ?: "",
                variant = LightTextVariant.Paragraph,
                lighten = true,
                maxLines = 6,
                align = TextAlign.Center,
            )
        } else {
            LightText(text = revealHint, variant = LightTextVariant.Fine, lighten = true, align = TextAlign.Center)
        }
    }
}
