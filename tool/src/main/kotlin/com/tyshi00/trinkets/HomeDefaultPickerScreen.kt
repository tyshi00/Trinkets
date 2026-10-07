package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen

/** Only offers [visibleOptions]. Home default should never be set to a feature that's currently turned off. */
class HomeDefaultPickerScreen(
    sealedActivity: SealedLightActivity,
    private val current: HomeDefault,
    private val visibleOptions: List<HomeDefault>,
) : SimpleLightScreen<HomeDefault>(sealedActivity) {

    @Composable
    override fun Content() {
        SettingsScaffold(title = "Home shows", onBack = { goBack(null) }) {
            visibleOptions.forEach { option ->
                SettingsChoiceRow(
                    title = option.label,
                    selected = option == current,
                    onClick = { goBack(option) },
                )
            }
        }
    }
}
