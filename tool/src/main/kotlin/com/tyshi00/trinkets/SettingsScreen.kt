package com.tyshi00.trinkets

import androidx.compose.foundation.background
import com.thelightphone.sdk.ui.lightClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
                    center = LightTopBarCenter.Text("Settings"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                LightScrollView(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 1f.gridUnitsAsDp()),
                ) {
                    // Invert colors
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable { viewModel.toggleInvertColors() }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LightIcon(
                            icon = if (state.invertColors) LightIcons.TOGGLE_OFF else LightIcons.TOGGLE_ON,
                        )
                        Spacer(modifier = Modifier.width(0.75f.gridUnitsAsDp()))
                        LightText(
                            text = "Invert screen color",
                            variant = LightTextVariant.Copy,
                            modifier = Modifier.weight(1f),
                        )
                    }

                    // Home screen default
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable {
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
                            }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            LightText(text = "Home screen shows", variant = LightTextVariant.Copy)
                            LightText(text = state.homeDefault.label, variant = LightTextVariant.Fine, lighten = true)
                        }
                    }

                    // Split home screen, with the two slot pickers nested
                    // underneath and only shown while it's switched on.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable { viewModel.toggleSplitHome() }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LightIcon(icon = if (state.splitHomeEnabled) LightIcons.TOGGLE_OFF else LightIcons.TOGGLE_ON)
                        Spacer(modifier = Modifier.width(0.75f.gridUnitsAsDp()))
                        Column(modifier = Modifier.weight(1f)) {
                            LightText(text = "Split home screen", variant = LightTextVariant.Copy)
                            LightText(
                                text = "Shows two features at once, stacked",
                                variant = LightTextVariant.Fine,
                                lighten = true,
                            )
                        }
                    }

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

                        SplitSlotRow(
                            label = "Top feature",
                            value = state.splitPrimary.label,
                            onClick = {
                                navigateTo(
                                    screenFactory = {
                                        SplitSlotPickerScreen(it, "Top feature", state.splitPrimary, slotOptions)
                                    },
                                    resultCallback = { result -> if (result != null) viewModel.setSplitPrimary(result) },
                                )
                            },
                        )

                        SplitSlotRow(
                            label = "Bottom feature",
                            value = state.splitSecondary.label,
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

                    Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
                    LightText(text = "FEATURES", variant = LightTextVariant.Detail, lighten = true)

                    FeatureToggleRow("Poem of the Day", state.visibility.poemEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.POEM)
                    }
                    FeatureToggleRow("Literary excerpt", state.visibility.excerptEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.EXCERPT)
                    }
                    FeatureToggleRow("Today in History", state.visibility.historyEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.HISTORY)
                    }
                    FeatureToggleRow("Reflection", state.visibility.reflectionEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.REFLECTION)
                    }
                    FeatureToggleRow("Philosophy prompt", state.visibility.philosophyEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.PHILOSOPHY)
                    }
                    FeatureToggleRow("Morning prompt", state.visibility.morningEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.MORNING)
                    }
                    FeatureToggleRow("Joke of the Day", state.visibility.jokeEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.JOKE)
                    }
                    FeatureToggleRow("Trivia", state.visibility.triviaEnabled) {
                        viewModel.toggleFeature(TrinketsFeature.TRIVIA)
                    }

                    // Motivation intensity, only meaningful while Morning Prompt is on.
                    if (state.visibility.morningEnabled) {
                        Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .lightClickable {
                                    navigateTo(
                                        screenFactory = { IntensityPickerScreen(it, state.motivationIntensity) },
                                        resultCallback = { result -> viewModel.setMotivationIntensity(result) },
                                    )
                                }
                                .padding(vertical = 0.75f.gridUnitsAsDp()),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                LightText(text = "Morning prompt intensity", variant = LightTextVariant.Copy)
                                LightText(
                                    text = state.motivationIntensity?.label ?: "Any (default)",
                                    variant = LightTextVariant.Fine,
                                    lighten = true,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
                    LightText(text = "COUNTDOWNS", variant = LightTextVariant.Detail, lighten = true)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable { viewModel.toggleCountdownTimer() }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        LightIcon(icon = if (state.countdownTimerEnabled) LightIcons.TOGGLE_OFF else LightIcons.TOGGLE_ON)
                        Spacer(modifier = Modifier.width(0.75f.gridUnitsAsDp()))
                        Column(modifier = Modifier.weight(1f)) {
                            LightText(text = "Countdown timer", variant = LightTextVariant.Copy)
                            LightText(
                                text = "Shows hours, minutes, and seconds alongside days",
                                variant = LightTextVariant.Fine,
                                lighten = true,
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable {
                                navigateTo(
                                    screenFactory = { DateFormatPickerScreen(it, state.dateFormat) },
                                    resultCallback = { result -> if (result != null) viewModel.setDateFormat(result) },
                                )
                            }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            LightText(text = "Default date format", variant = LightTextVariant.Copy)
                            LightText(text = state.dateFormat.label, variant = LightTextVariant.Fine, lighten = true)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable {
                                navigateTo(
                                    screenFactory = { TimeFormatPickerScreen(it, state.timeFormat) },
                                    resultCallback = { result -> if (result != null) viewModel.setTimeFormat(result) },
                                )
                            }
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            LightText(text = "Default time format", variant = LightTextVariant.Copy)
                            LightText(text = state.timeFormat.label, variant = LightTextVariant.Fine, lighten = true)
                        }
                    }

                    Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))

                    LightText(
                        text = "Reset all data",
                        variant = LightTextVariant.Copy,
                        lighten = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .lightClickable {
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
                            .padding(vertical = 0.75f.gridUnitsAsDp()),
                    )
                }

                LightBottomBar(items = listOf())
            }
        }
    }
}

/** An indented picker row for one half of the split Home screen. */
@Composable
private fun SplitSlotRow(label: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .lightClickable(onClick = onClick)
            .padding(start = 2.5f.gridUnitsAsDp(), top = 0.5f.gridUnitsAsDp(), bottom = 0.5f.gridUnitsAsDp()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            LightText(text = label, variant = LightTextVariant.Copy)
            LightText(text = value, variant = LightTextVariant.Fine, lighten = true)
        }
    }
}

@Composable
private fun FeatureToggleRow(label: String, enabled: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .lightClickable(onClick = onToggle)
            .padding(vertical = 0.75f.gridUnitsAsDp()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LightIcon(icon = if (enabled) LightIcons.TOGGLE_OFF else LightIcons.TOGGLE_ON)
        Spacer(modifier = Modifier.width(0.75f.gridUnitsAsDp()))
        LightText(text = label, variant = LightTextVariant.Copy, modifier = Modifier.weight(1f))
    }
}
