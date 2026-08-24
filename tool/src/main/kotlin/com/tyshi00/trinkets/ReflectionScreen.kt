package com.tyshi00.trinkets

import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.buildDatabase
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightIcons
import com.thelightphone.sdk.ui.LightShakeDetector
import com.thelightphone.sdk.ui.LightText
import com.thelightphone.sdk.ui.LightTextVariant
import com.thelightphone.sdk.ui.LightTheme
import com.thelightphone.sdk.ui.LightThemeController
import com.thelightphone.sdk.ui.LightThemeTokens
import com.thelightphone.sdk.ui.LightTopBar
import com.thelightphone.sdk.ui.LightTopBarCenter
import com.thelightphone.sdk.ui.gridUnitsAsDp
import com.thelightphone.sdk.ui.lightClickable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ReflectionState(
    val prompts: List<ReflectionPrompt> = emptyList(),
    val index: Int = 0,
    val usedIds: Set<Int> = emptySet(),
    val loaded: Boolean = false,
) {
    val current: ReflectionPrompt? get() = prompts.getOrNull(index)
    val currentIsUsed: Boolean get() = current?.let { it.id in usedIds } ?: false
}

class ReflectionViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {

    private val _state = MutableStateFlow(ReflectionState())
    val state: StateFlow<ReflectionState> = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val used = repo.getUsedReflectionIds()
            _state.value = ReflectionState(
                prompts = ContentRepository.reflectionPromptsForToday(used),
                index = 0,
                usedIds = used,
                loaded = true,
            )
        }
    }

    /** Advances to the next of today's prompts, wrapping around. */
    fun shuffle() {
        val current = _state.value
        if (current.prompts.size <= 1) return
        _state.value = current.copy(index = (current.index + 1) % current.prompts.size)
    }

    /**
     * Checks the current prompt off (or un-checks it). Checked prompts drop out
     * of future daily draws until the whole pool has been used, which is what
     * keeps prompts from repeating.
     *
     * Today's visible set deliberately isn't re-drawn here, so the list doesn't
     * shift under the person mid-session; the change takes effect tomorrow.
     */
    fun toggleUsed() {
        val prompt = _state.value.current ?: return
        val used = _state.value.usedIds
        val updated = if (prompt.id in used) used - prompt.id else used + prompt.id
        _state.value = _state.value.copy(usedIds = updated)
        viewModelScope.launch(Dispatchers.IO) {
            repo.setUsedReflectionIds(updated)
        }
    }
}

/**
 * Reflection: an open question to sit with. Tap or shake to move through the
 * day's prompts, and check one off once it's been used so it stops coming back
 * until the rest of the pool has cycled through.
 */
class ReflectionScreen(
    sealedActivity: SealedLightActivity,
) : SimpleLightScreen<Unit>(sealedActivity) {

    private val repo by lazy {
        TrinketsRepository.getInstance {
            lightContext.buildDatabase(TrinketsDatabase::class.java, "trinkets.db")
        }
    }

    private val viewModel by lazy { ReflectionViewModel(repo) }

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        val state by viewModel.state.collectAsState()

        LightShakeDetector(onShake = viewModel::shuffle)

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
                    center = LightTopBarCenter.Text("Reflection"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                val prompt = state.current
                if (prompt == null) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        contentAlignment = Alignment.Center,
                    ) {
                        LightText(
                            text = if (state.loaded) "No prompts available." else "",
                            variant = LightTextVariant.Copy,
                            lighten = true,
                            align = TextAlign.Center,
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 1f.gridUnitsAsDp())
                            .lightClickable { viewModel.shuffle() },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LightText(
                            text = "${state.index + 1} OF ${state.prompts.size}",
                            variant = LightTextVariant.Detail,
                            lighten = true,
                            align = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
                        LightText(
                            text = prompt.text,
                            variant = LightTextVariant.ParagraphWide,
                            align = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
                        LightText(
                            text = if (state.currentIsUsed) "USED" else "TAP OR SHAKE FOR ANOTHER",
                            variant = LightTextVariant.Detail,
                            lighten = true,
                            align = TextAlign.Center,
                        )
                    }
                }

                // Centered check button: nulls on either side keep it in the
                // middle, matching how Countdowns centers its Add button.
                LightBottomBar(
                    items = listOf(
                        null,
                        if (state.currentIsUsed) {
                            LightBarButton.Icon(
                                painter = painterResource(R.drawable.ic_check_circle_filled),
                                contentDescription = "Mark prompt unused",
                                onClick = { viewModel.toggleUsed() },
                            )
                        } else {
                            LightBarButton.LightIcon(
                                icon = LightIcons.CIRCLE,
                                contentDescription = "Mark prompt used",
                                onClick = { viewModel.toggleUsed() },
                            )
                        },
                        null,
                    ),
                )
            }
        }
    }
}
