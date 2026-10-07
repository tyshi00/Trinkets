package com.tyshi00.trinkets

import androidx.compose.runtime.Composable
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen

/**
 * Picks the feature for one half of the split Home screen. [title] distinguishes
 * the top slot from the bottom one, and [visibleOptions] omits any feature that's
 * currently switched off in Settings.
 */
class SplitSlotPickerScreen(
    sealedActivity: SealedLightActivity,
    private val title: String,
    private val current: SplitSlot,
    private val visibleOptions: List<SplitSlot>,
) : SimpleLightScreen<SplitSlot>(sealedActivity) {

    @Composable
    override fun Content() {
        SettingsScaffold(title = title, onBack = { goBack(null) }) {
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
