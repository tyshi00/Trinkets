package com.tyshi00.trinkets

import androidx.compose.foundation.background
import com.thelightphone.sdk.ui.lightClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightIcon
import com.thelightphone.sdk.ui.LightIcons
import com.thelightphone.sdk.ui.LightScrollView
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

data class CountdownDisplayItem(
    val id: Long,
    val name: String,
    val date: String,
    val dateDisplay: String,
    val countdownDisplay: String,
    val notes: String?,
)

data class CountdownListState(
    val items: List<CountdownDisplayItem> = emptyList(),
    val canAddMore: Boolean = true,
    val loaded: Boolean = false,
    val timerEnabled: Boolean = false,
    /** Starred countdown shown on Home; null falls back to the soonest upcoming. */
    val featuredId: Long? = null,
)

class CountdownListViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {
    private val _state = MutableStateFlow(CountdownListState())
    val state: StateFlow<CountdownListState> = _state.asStateFlow()

    override fun onScreenShow(screen: SimpleLightScreen<Unit>) {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            val entries = repo.getCountdowns()
            val dateFormat = repo.getDateFormat()
            val timerEnabled = repo.getCountdownTimerEnabled()
            val featuredId = repo.getFeaturedCountdownId()
            val items = entries
                .map {
                    CountdownDisplayItem(
                        id = it.id,
                        name = it.name,
                        date = it.date,
                        dateDisplay = dateLabel(it.date, dateFormat),
                        countdownDisplay = countdownLabel(it.date),
                        notes = it.notes,
                    )
                }
                // Soonest-upcoming (smallest non-negative days-until) first,
                // then past dates ordered most-recent-first.
                .sortedBy { daysUntil(it.date) }
            _state.value = CountdownListState(
                items = items,
                canAddMore = entries.size < MAX_COUNTDOWNS,
                loaded = true,
                timerEnabled = timerEnabled,
                featuredId = featuredId,
            )
        }
    }

    /**
     * Tapping the star sets that countdown as the one Home shows; tapping the
     * already-starred one clears the pick and reverts to the soonest upcoming.
     */
    fun toggleFeatured(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val newValue = if (_state.value.featuredId == id) null else id
            repo.setFeaturedCountdownId(newValue)
            _state.value = _state.value.copy(featuredId = newValue)
        }
    }
}

class CountdownScreen(
    sealedActivity: SealedLightActivity,
    private val repo: TrinketsRepository,
) : LightScreen<Unit, CountdownListViewModel>(sealedActivity) {

    override val viewModelClass: Class<CountdownListViewModel>
        get() = CountdownListViewModel::class.java

    override fun createViewModel() = CountdownListViewModel(repo)

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        val state by viewModel.state.collectAsState()

        LightTheme(colors = themeColors) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightThemeTokens.colors.background),
            ) {
                LightTopBar(
                    leftButton = LightBarButton.LightIcon(
                        icon = LightIcons.BACK,
                        onClick = { goBack() },
                    ),
                    center = LightTopBarCenter.Text("Countdowns"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                if (state.loaded && state.items.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        contentAlignment = Alignment.Center,
                    ) {
                        LightText(
                            text = "No countdowns yet. Add up to $MAX_COUNTDOWNS.",
                            variant = LightTextVariant.Copy,
                            lighten = true,
                            align = TextAlign.Center,
                        )
                    }
                } else {
                    LightScrollView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                    ) {
                        state.items.forEachIndexed { index, item ->
                            CountdownRow(
                                item = item,
                                timerEnabled = state.timerEnabled,
                                // Nothing is starred until the person picks one.
                                // Home still falls back to the soonest upcoming
                                // on its own, so an empty star here means "auto"
                                // rather than "nothing will show".
                                isFeatured = state.featuredId == item.id,
                                onToggleFeatured = { viewModel.toggleFeatured(item.id) },
                                onEdit = {
                                    navigateTo(
                                        screenFactory = { CountdownEditScreen(it, repo, existing = item) },
                                        resultCallback = { changed -> if (changed == true) viewModel.reload() },
                                    )
                                },
                            )
                            if (index != state.items.lastIndex) {
                                Spacer(modifier = Modifier.height(1.25f.gridUnitsAsDp()))
                            }
                        }
                    }
                }

                LightBottomBar(
                    items = listOf(
                        null,
                        LightBarButton.LightIcon(
                            icon = LightIcons.ADD,
                            contentDescription = "Add countdown",
                            onClick = {
                                navigateTo(
                                    screenFactory = { CountdownEditScreen(it, repo, existing = null) },
                                    resultCallback = { saved -> if (saved == true) viewModel.reload() },
                                )
                            },
                        ).takeIf { state.canAddMore },
                        null,
                    ),
                )
            }
        }
    }
}

@Composable
private fun CountdownRow(
    item: CountdownDisplayItem,
    timerEnabled: Boolean,
    isFeatured: Boolean,
    onToggleFeatured: () -> Unit,
    onEdit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.5f.gridUnitsAsDp()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            LightText(text = item.name, variant = LightTextVariant.Copy)
            if (timerEnabled) {
                LiveCountdownLine(isoDate = item.date, dateDisplay = item.dateDisplay)
            } else {
                LightText(
                    text = "${item.countdownDisplay} \u00B7 ${item.dateDisplay}",
                    variant = LightTextVariant.Fine,
                    lighten = true,
                )
            }
            if (!item.notes.isNullOrBlank()) {
                LightText(
                    text = item.notes,
                    variant = LightTextVariant.Fine,
                    lighten = true,
                    maxLines = 2,
                )
            }
        }
        Box(
            modifier = Modifier
                .lightClickable(onClick = onToggleFeatured)
                .padding(0.5f.gridUnitsAsDp()),
        ) {
            LightIcon(
                icon = if (isFeatured) LightIcons.STAR else LightIcons.STAR_OUTLINE,
                size = 1.5f,
                contentDescription = if (isFeatured) {
                    "${item.name} is shown on home"
                } else {
                    "Show ${item.name} on home"
                },
            )
        }
        Box(
            modifier = Modifier
                .lightClickable(onClick = onEdit)
                .padding(0.5f.gridUnitsAsDp()),
        ) {
            LightIcon(icon = LightIcons.PENCIL, size = 1.5f, contentDescription = "Edit ${item.name}")
        }
    }
}

/** Live, second-by-second "Nd HHh MMm SSs" line that ticks while this row is on screen. */
@Composable
private fun LiveCountdownLine(isoDate: String, dateDisplay: String) {
    var timer by remember(isoDate) { mutableStateOf(countdownTimerFor(isoDate)) }

    LaunchedEffect(isoDate) {
        while (true) {
            delay(1000)
            timer = countdownTimerFor(isoDate)
        }
    }

    val prefix = if (timer.isPast) "Started" else "In"
    LightText(
        text = "$prefix ${timer.shortLabel()} \u00B7 $dateDisplay",
        variant = LightTextVariant.Fine,
        lighten = true,
    )
}
