package com.tyshi00.trinkets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.thelightphone.sdk.LightScreen
import com.thelightphone.sdk.LightViewModel
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.ui.LightThemeController
import com.thelightphone.sdk.ui.gridUnitsAsDp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SettingsState(
    val invertColors: Boolean = false,
    val homeDefault: HomeDefault = HomeDefault.COUNTDOWN,
    val motivationIntensity: MotivationIntensity? = null,
    val visibility: FeatureVisibility = FeatureVisibility(),
    val countdownTimerEnabled: Boolean = false,
    val dateFormat: DateFormat = DateFormat.MDY,
    val timeFormat: TimeFormat = TimeFormat.AM_PM,
    val splitHomeEnabled: Boolean = false,
    val splitPrimary: SplitSlot = SplitSlot.COUNTDOWN,
    val splitSecondary: SplitSlot = SplitSlot.MORNING,
)

class SettingsViewModel(private val repo: TrinketsRepository) : LightViewModel<Unit>() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    override fun onScreenShow(screen: SimpleLightScreen<Unit>) {
        reload()
    }

    private fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = SettingsState(
                invertColors = repo.getInvertColors(),
                homeDefault = repo.getHomeDefault(),
                motivationIntensity = repo.getMotivationIntensity(),
                visibility = repo.getFeatureVisibility(),
                countdownTimerEnabled = repo.getCountdownTimerEnabled(),
                dateFormat = repo.getDateFormat(),
                timeFormat = repo.getTimeFormat(),
                splitHomeEnabled = repo.getSplitHomeEnabled(),
                splitPrimary = repo.getSplitPrimary(),
                splitSecondary = repo.getSplitSecondary(),
            )
        }
    }

    fun toggleInvertColors() {
        viewModelScope.launch(Dispatchers.IO) {
            val newValue = !_state.value.invertColors
            repo.setInvertColors(newValue)
            _state.value = _state.value.copy(invertColors = newValue)
            if (newValue) LightThemeController.setLightTheme() else LightThemeController.setDarkTheme()
        }
    }

    fun setHomeDefault(value: HomeDefault) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setHomeDefault(value)
            _state.value = _state.value.copy(homeDefault = value)
        }
    }

    fun toggleSplitHome() {
        viewModelScope.launch(Dispatchers.IO) {
            val newValue = !_state.value.splitHomeEnabled
            repo.setSplitHomeEnabled(newValue)
            _state.value = _state.value.copy(splitHomeEnabled = newValue)
        }
    }

    /**
     * Both halves picking the same feature would just show it twice, so if the
     * new primary matches the current secondary the two are swapped instead.
     */
    fun setSplitPrimary(value: SplitSlot) {
        viewModelScope.launch(Dispatchers.IO) {
            val current = _state.value
            if (value == current.splitSecondary) {
                repo.setSplitSecondary(current.splitPrimary)
                repo.setSplitPrimary(value)
                _state.value = current.copy(splitPrimary = value, splitSecondary = current.splitPrimary)
            } else {
                repo.setSplitPrimary(value)
                _state.value = current.copy(splitPrimary = value)
            }
        }
    }

    fun setSplitSecondary(value: SplitSlot) {
        viewModelScope.launch(Dispatchers.IO) {
            val current = _state.value
            if (value == current.splitPrimary) {
                repo.setSplitPrimary(current.splitSecondary)
                repo.setSplitSecondary(value)
                _state.value = current.copy(splitSecondary = value, splitPrimary = current.splitSecondary)
            } else {
                repo.setSplitSecondary(value)
                _state.value = current.copy(splitSecondary = value)
            }
        }
    }

    fun setMotivationIntensity(value: MotivationIntensity?) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setMotivationIntensity(value)
            _state.value = _state.value.copy(motivationIntensity = value)
        }
    }

    fun toggleFeature(feature: TrinketsFeature) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentlyEnabled = _state.value.visibility.isEnabled(feature)
            repo.setFeatureEnabled(feature, !currentlyEnabled)
            reload()
        }
    }

    fun toggleCountdownTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            val newValue = !_state.value.countdownTimerEnabled
            repo.setCountdownTimerEnabled(newValue)
            _state.value = _state.value.copy(countdownTimerEnabled = newValue)
        }
    }

    fun setDateFormat(value: DateFormat) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setDateFormat(value)
            _state.value = _state.value.copy(dateFormat = value)
        }
    }

    fun setTimeFormat(value: TimeFormat) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.setTimeFormat(value)
            _state.value = _state.value.copy(timeFormat = value)
        }
    }

    fun resetAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.resetAll()
            reload()
        }
    }
}

class SettingsScreen(
    sealedActivity: SealedLightActivity,
    private val repo: TrinketsRepository,
) : LightScreen<Unit, SettingsViewModel>(sealedActivity) {

    override val viewModelClass: Class<SettingsViewModel>
        get() = SettingsViewModel::class.java

    override fun createViewModel() = SettingsViewModel(repo)

    @Composable
    override fun Content() {
        val state by viewModel.state.collectAsState()

        SettingsScaffold(title = "Settings", onBack = { goBack() }) {
            SettingsOptionRow(
                title = "Invert screen color",
                checked = state.invertColors,
                onClick = { viewModel.toggleInvertColors() },
            )

            SettingsOptionRow(
                title = "Home screen shows",
                subtitle = state.homeDefault.label,
                onClick = {
                    val visibleOptions = buildList {
                        add(HomeDefault.COUNTDOWN)
                        if (state.visibility.poemEnabled) add(HomeDefault.POEM)
                        if (state.visibility.excerptEnabled) add(HomeDefault.EXCERPT)
                        if (state.visibility.historyEnabled) add(HomeDefault.HISTORY)
                        if (state.visibility.reflectionEnabled) add(HomeDefault.REFLECTION)
                        if (state.visibility.philosophyEnabled) add(HomeDefault.PHILOSOPHY)
                        if (state.visibility.morningEnabled) add(HomeDefault.MORNING)
                        if (state.visibility.jokeEnabled) add(HomeDefault.JOKE)
                        if (state.visibility.triviaEnabled) add(HomeDefault.TRIVIA)
                    }
                    navigateTo(
                        screenFactory = { HomeDefaultPickerScreen(it, state.homeDefault, visibleOptions) },
                        resultCallback = { result -> if (result != null) viewModel.setHomeDefault(result) },
                    )
                },
            )

            // Split home screen, with the two slot pickers nested underneath
            // and only shown while it's switched on.
            SettingsOptionRow(
                title = "Split home screen",
                subtitle = "Shows two features at once, stacked",
                checked = state.splitHomeEnabled,
                onClick = { viewModel.toggleSplitHome() },
            )

            if (state.splitHomeEnabled) {
                val slotOptions = buildList {
                    add(SplitSlot.COUNTDOWN)
                    if (state.visibility.poemEnabled) add(SplitSlot.POEM)
                    if (state.visibility.excerptEnabled) add(SplitSlot.EXCERPT)
                    if (state.visibility.historyEnabled) add(SplitSlot.HISTORY)
                    if (state.visibility.reflectionEnabled) add(SplitSlot.REFLECTION)
                    if (state.visibility.philosophyEnabled) add(SplitSlot.PHILOSOPHY)
                    if (state.visibility.morningEnabled) add(SplitSlot.MORNING)
                    if (state.visibility.jokeEnabled) add(SplitSlot.JOKE)
                    if (state.visibility.triviaEnabled) add(SplitSlot.TRIVIA)
                }

                SettingsOptionRow(
                    title = "Top feature",
                    subtitle = state.splitPrimary.label,
                    nested = true,
                    onClick = {
                        navigateTo(
                            screenFactory = {
                                SplitSlotPickerScreen(it, "Top feature", state.splitPrimary, slotOptions)
                            },
                            resultCallback = { result -> if (result != null) viewModel.setSplitPrimary(result) },
                        )
                    },
                )

                SettingsOptionRow(
                    title = "Bottom feature",
                    subtitle = state.splitSecondary.label,
                    nested = true,
                    onClick = {
                        navigateTo(
                            screenFactory = {
                                SplitSlotPickerScreen(it, "Bottom feature", state.splitSecondary, slotOptions)
                            },
                            resultCallback = { result -> if (result != null) viewModel.setSplitSecondary(result) },
                        )
                    },
                )
            }

            SettingsSectionHeader("FEATURES")

            SettingsOptionRow(title = "Poem of the Day", checked = state.visibility.poemEnabled) {
                viewModel.toggleFeature(TrinketsFeature.POEM)
            }
            SettingsOptionRow(title = "Literary excerpt", checked = state.visibility.excerptEnabled) {
                viewModel.toggleFeature(TrinketsFeature.EXCERPT)
            }
            SettingsOptionRow(title = "Today in History", checked = state.visibility.historyEnabled) {
                viewModel.toggleFeature(TrinketsFeature.HISTORY)
            }
            SettingsOptionRow(title = "Reflection", checked = state.visibility.reflectionEnabled) {
                viewModel.toggleFeature(TrinketsFeature.REFLECTION)
            }
            SettingsOptionRow(title = "Philosophy prompt", checked = state.visibility.philosophyEnabled) {
                viewModel.toggleFeature(TrinketsFeature.PHILOSOPHY)
            }
            SettingsOptionRow(title = "Morning prompt", checked = state.visibility.morningEnabled) {
                viewModel.toggleFeature(TrinketsFeature.MORNING)
            }
            SettingsOptionRow(title = "Joke of the Day", checked = state.visibility.jokeEnabled) {
                viewModel.toggleFeature(TrinketsFeature.JOKE)
            }
            SettingsOptionRow(title = "Trivia", checked = state.visibility.triviaEnabled) {
                viewModel.toggleFeature(TrinketsFeature.TRIVIA)
            }

            // Motivation intensity, only meaningful while Morning Prompt is on.
            if (state.visibility.morningEnabled) {
                SettingsOptionRow(
                    title = "Morning prompt intensity",
                    subtitle = state.motivationIntensity?.label ?: "Any (default)",
                    onClick = {
                        navigateTo(
                            screenFactory = { IntensityPickerScreen(it, state.motivationIntensity) },
                            resultCallback = { result -> viewModel.setMotivationIntensity(result) },
                        )
                    },
                )
            }

            SettingsSectionHeader("COUNTDOWNS")

            SettingsOptionRow(
                title = "Countdown timer",
                subtitle = "Shows hours, minutes, and seconds alongside days",
                checked = state.countdownTimerEnabled,
                onClick = { viewModel.toggleCountdownTimer() },
            )

            SettingsOptionRow(
                title = "Default date format",
                subtitle = state.dateFormat.label,
                onClick = {
                    navigateTo(
                        screenFactory = { DateFormatPickerScreen(it, state.dateFormat) },
                        resultCallback = { result -> if (result != null) viewModel.setDateFormat(result) },
                    )
                },
            )

            SettingsOptionRow(
                title = "Default time format",
                subtitle = state.timeFormat.label,
                onClick = {
                    navigateTo(
                        screenFactory = { TimeFormatPickerScreen(it, state.timeFormat) },
                        resultCallback = { result -> if (result != null) viewModel.setTimeFormat(result) },
                    )
                },
            )

            Spacer(modifier = Modifier.height(1f.gridUnitsAsDp()))

            SettingsDangerRow(title = "Reset all data") {
                navigateTo(
                    screenFactory = {
                        ConfirmResetScreen(
                            it,
                            "Reset all data? This will permanently clear every countdown and every preference.",
                        )
                    },
                    resultCallback = { confirmed -> if (confirmed == true) viewModel.resetAll() },
                )
            }
        }
    }
}
