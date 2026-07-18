package com.tyshi00.trinkets

import androidx.compose.foundation.background
import com.thelightphone.sdk.ui.lightClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FeaturesListState(
    val visibility: FeatureVisibility = FeatureVisibility(),
    val motivationIntensity: MotivationIntensity? = null,
)

class FeaturesListViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {
    private val _state = MutableStateFlow(FeaturesListState())
    val state: StateFlow<FeaturesListState> = _state.asStateFlow()

    override fun onScreenShow(screen: SimpleLightScreen<Unit>) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = FeaturesListState(
                visibility = repo.getFeatureVisibility(),
                motivationIntensity = repo.getMotivationIntensity(),
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
                    center = LightTopBarCenter.Text("Features"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                val visible = TrinketsFeature.entries.filter { state.visibility.isEnabled(it) }

                if (visible.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        contentAlignment = Alignment.Center,
                    ) {
                        LightText(
                            text = "Every feature is turned off. Enable some in Settings.",
                            variant = LightTextVariant.Copy,
                            lighten = true,
                        )
                    }
                } else {
                    LightScrollView(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                    ) {
                        visible.forEach { feature ->
                            LightText(
                                text = feature.label,
                                variant = LightTextVariant.Copy,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .lightClickable {
                                        navigateTo(
                                            screenFactory = {
                                                openFeatureScreen(it, feature, state.motivationIntensity)
                                            },
                                        )
                                    }
                                    .padding(vertical = 0.75f.gridUnitsAsDp()),
                            )
                        }
                    }
                }

                LightBottomBar(items = listOf())
            }
        }
    }
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
    TrinketsFeature.HISTORY -> HistoryScreen(sealedActivity)
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
