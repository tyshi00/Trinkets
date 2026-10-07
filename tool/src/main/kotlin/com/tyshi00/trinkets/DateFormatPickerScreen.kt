package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen

/** Picker for how countdown dates are displayed throughout the app. */
class DateFormatPickerScreen(
    sealedActivity: SealedLightActivity,
    private val currentFormat: DateFormat,
) : SimpleLightScreen<DateFormat>(sealedActivity) {

    @Composable
    override fun Content() {
        SettingsScaffold(title = "Default date format", onBack = { goBack(null) }) {
            DateFormat.entries.forEach { option ->
                SettingsChoiceRow(
                    title = option.label,
                    selected = option == currentFormat,
                    onClick = { goBack(option) },
                )
            }
        }
    }
}
