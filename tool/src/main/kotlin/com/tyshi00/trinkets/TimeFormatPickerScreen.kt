package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen

/** Picker for 12-hour (AM/PM) vs 24-hour clock display. */
class TimeFormatPickerScreen(
    sealedActivity: SealedLightActivity,
    private val currentFormat: TimeFormat,
) : SimpleLightScreen<TimeFormat>(sealedActivity) {

    @Composable
    override fun Content() {
        SettingsScaffold(title = "Default time format", onBack = { goBack(null) }) {
            TimeFormat.entries.forEach { option ->
                SettingsChoiceRow(
                    title = option.label,
                    selected = option == currentFormat,
                    onClick = { goBack(option) },
                )
            }
        }
    }
}
