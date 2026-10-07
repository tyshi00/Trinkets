package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen

/** Null in the returned value means "Any." Morning Prompt rotates across the full gentle-to-energizing range. */
class IntensityPickerScreen(
    sealedActivity: SealedLightActivity,
    private val current: MotivationIntensity?,
) : SimpleLightScreen<MotivationIntensity?>(sealedActivity) {

    @Composable
    override fun Content() {
        // Backing out without choosing keeps the current setting, since this
        // screen's result type can't distinguish "Any" from "cancelled".
        SettingsScaffold(title = "Motivation intensity", onBack = { goBack(current) }) {
            SettingsChoiceRow(
                title = "Any (default)",
                selected = current == null,
                onClick = { goBack(null) },
            )
            MotivationIntensity.entries.forEach { option ->
                SettingsChoiceRow(
                    title = option.label,
                    selected = option == current,
                    onClick = { goBack(option) },
                )
            }
        }
    }
}
