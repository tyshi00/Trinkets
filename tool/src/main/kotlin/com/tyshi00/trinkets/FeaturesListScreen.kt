package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FeaturesListState(
    val visibility: FeatureVisibility = FeatureVisibility(),
    val motivationIntensity: MotivationIntensity? = null,
    /**
     * Features already shown on Home, so the list doesn't just repeat them.
     * In classic mode that's the single Home default; in split mode it's both
     * halves. Countdowns isn't a content feature, so it contributes nothing.
     */
    val featuresOnHome: Set<TrinketsFeature> = emptySet(),
)

class FeaturesListViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {
    private val _state = MutableStateFlow(FeaturesListState())
    val state: StateFlow<FeaturesListState> = _state.asStateFlow()

    override fun onScreenShow(screen: SimpleLightScreen<Unit>) {
        viewModelScope.launch(Dispatchers.IO) {
            val onHome = if (repo.getSplitHomeEnabled()) {
                setOfNotNull(repo.getSplitPrimary().toFeature(), repo.getSplitSecondary().toFeature())
            } else {
                setOfNotNull(homeDefaultToFeatureOrNull(repo.getHomeDefault()))
            }

            _state.value = FeaturesListState(
                visibility = repo.getFeatureVisibility(),
                motivationIntensity = repo.getMotivationIntensity(),
                featuresOnHome = onHome,
            )
        }
    }
}

/** Middle bottom-bar destination, a directory of every rotating content feature. */
class FeaturesListScreen(
    sealedActivity: SealedLightActivity,
    private val repo: TrinketsRepository,
) : LightScreen<Unit, FeaturesListViewModel>(sealedActivity) {

    override val viewModelClass: Class<FeaturesListViewModel>
        get() = FeaturesListViewModel::class.java

    override fun createViewModel() = FeaturesListViewModel(repo)

    @Composable
    override fun Content() {
        val state by viewModel.state.collectAsState()

        val visible = TrinketsFeature.entries
            .filter { state.visibility.isEnabled(it) }
            .filterNot { it in state.featuresOnHome }

        SettingsScaffold(
            title = "Features",
            onBack = { goBack() },
            scroll = visible.isNotEmpty(),
        ) {
            if (visible.isEmpty()) {
                SettingsEmptyMessage(
                    if (state.featuresOnHome.isEmpty()) {
                        "Every feature is turned off. Enable some in Settings."
                    } else {
                        "Everything that's on is already showing on your home screen."
                    },
                )
            } else {
                visible.forEach { feature ->
                    SettingsOptionRow(title = feature.label) {
                        navigateTo(
                            screenFactory = {
                                openFeatureScreen(it, feature, state.motivationIntensity)
                            },
                        )
                    }
                }
            }
        }
    }
}

/** Null for Countdowns, which is not one of the rotating content features. */
fun homeDefaultToFeatureOrNull(homeDefault: HomeDefault): TrinketsFeature? = when (homeDefault) {
    HomeDefault.COUNTDOWN -> null
    HomeDefault.POEM -> TrinketsFeature.POEM
    HomeDefault.EXCERPT -> TrinketsFeature.EXCERPT
    HomeDefault.HISTORY -> TrinketsFeature.HISTORY
    HomeDefault.PHILOSOPHY -> TrinketsFeature.PHILOSOPHY
    HomeDefault.MORNING -> TrinketsFeature.MORNING
    HomeDefault.JOKE -> TrinketsFeature.JOKE
    HomeDefault.TRIVIA -> TrinketsFeature.TRIVIA
    HomeDefault.REFLECTION -> TrinketsFeature.REFLECTION
}

/** Builds the right screen instance (with today's content baked in) for a given feature. */
fun openFeatureScreen(
    sealedActivity: SealedLightActivity,
    feature: TrinketsFeature,
    motivationIntensity: MotivationIntensity?,
): SimpleLightScreen<Unit> = when (feature) {
    TrinketsFeature.POEM -> {
        val poem = ContentRepository.poemOfTheDay()
        TextContentScreen(
            sealedActivity,
            topBarTitle = "Poem of the Day",
            heading = poem?.title.orEmpty(),
            body = poem?.body.orEmpty(),
            secondaryLine = poem?.author?.let { "— $it" },
            bodyAlign = TextAlign.Start,
            emptyMessage = "No poems have been added yet.",
        )
    }
    TrinketsFeature.EXCERPT -> {
        val excerpt = ContentRepository.excerptOfTheDay()
        TextContentScreen(
            sealedActivity,
            topBarTitle = "Literary Excerpt",
            heading = "",
            body = excerpt?.let { "\u201C${it.quote}\u201D" }.orEmpty(),
            secondaryLine = excerpt?.let { "${it.author}, ${it.work}" },
            emptyMessage = "No excerpts have been added yet.",
        )
    }
    TrinketsFeature.REFLECTION -> ReflectionScreen(sealedActivity)

    TrinketsFeature.HISTORY -> {
        val fact = ContentRepository.historyFactOfTheDay()
        TextContentScreen(
            sealedActivity,
            topBarTitle = "Today in History",
            heading = fact?.heading.orEmpty(),
            body = fact?.event.orEmpty(),
            emptyMessage = "No history facts logged for today yet.",
        )
    }
    TrinketsFeature.PHILOSOPHY -> {
        val prompt = ContentRepository.philosophyPromptOfTheDay()
        TextContentScreen(
            sealedActivity,
            topBarTitle = "Philosophy Prompt",
            heading = "",
            body = prompt?.prompt.orEmpty(),
            secondaryLine = prompt?.question,
            emptyMessage = "No philosophy prompts have been added yet.",
        )
    }
    TrinketsFeature.MORNING -> {
        val prompt = ContentRepository.morningPromptOfTheDay(motivationIntensity)
        TextContentScreen(
            sealedActivity,
            topBarTitle = "Morning Prompt",
            heading = prompt?.intensity?.label.orEmpty(),
            body = prompt?.text.orEmpty(),
            emptyMessage = "No morning prompts have been added yet.",
        )
    }
    TrinketsFeature.JOKE -> {
        val joke = ContentRepository.jokeOfTheDay()
        RevealContentScreen(
            sealedActivity,
            topBarTitle = "Joke of the Day",
            prompt = joke?.setup.orEmpty(),
            answer = joke?.punchline.orEmpty(),
            revealLabel = "TAP FOR THE PUNCHLINE",
            emptyMessage = "No jokes have been added yet.",
        )
    }
    TrinketsFeature.TRIVIA -> {
        val trivia = ContentRepository.triviaOfTheDay()
        RevealContentScreen(
            sealedActivity,
            topBarTitle = "Trivia",
            prompt = trivia?.question.orEmpty(),
            answer = trivia?.answer.orEmpty(),
            revealLabel = "TAP TO REVEAL THE ANSWER",
            emptyMessage = "No trivia has been added yet.",
        )
    }
}
