package com.tyshi00.trinkets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightBottomBarItem
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
import com.thelightphone.sdk.ui.designVerticalPxToDp
import com.thelightphone.sdk.ui.gridUnitsAsDp
import com.thelightphone.sdk.ui.lightClickable

/**
 * Shared chrome and list rows for every Settings screen.
 *
 * Every value here is taken from LightOS' own menus so the tool's settings sit
 * flush with the rest of the phone: row titles at [LightTextVariant.Heading]
 * (38sp), secondary values at [LightTextVariant.Detail] (20sp), a [ROW_UNITS]
 * tall single-line row, [SETTINGS_START_UNITS] of start padding and
 * [ROW_END_UNITS] of end padding on each row.
 *
 * Screens build out of these rather than laying out their own Rows, so the
 * spacing can't drift apart again one screen at a time.
 */

/** Height of a single-line row. */
const val ROW_UNITS = 5f

/** Start padding for the content column, applied once by [SettingsScaffold]. */
const val SETTINGS_START_UNITS = 2.1f

/** End padding on each row, so text stops short of the scroll bar. */
const val ROW_END_UNITS = 1f

/** Extra start indent for a row nested under the setting that reveals it. */
const val NESTED_START_UNITS = 2.1f

/** Vertical padding on a row that can wrap to two lines. */
private const val STACKED_ROW_UNITS = 1.3f

/** Gap above a section header. */
private const val SECTION_GAP_UNITS = 1f

/** Leading-icon column width, and the title's first-line height to centre it against. */
private const val ICON_COLUMN_UNITS = 2.78f
private const val HEADING_PX = 38f
private const val HEADING_LINE = 1.35f

/**
 * Top bar, content column and bottom bar, with the padding every Settings
 * screen shares. [bottom] is empty on most screens, which still draws the bar
 * so the content column ends at the same height throughout. Set [scroll] to
 * false for a screen whose content centres itself in the remaining space
 * instead of listing rows down it.
 */
@Composable
fun SettingsScaffold(
    title: String,
    onBack: () -> Unit,
    bottom: List<LightBottomBarItem?> = listOf(),
    scroll: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val themeColors by LightThemeController.colors.collectAsState()

    LightTheme(colors = themeColors) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightThemeTokens.colors.background),
        ) {
            LightTopBar(
                leftButton = LightBarButton.LightIcon(icon = LightIcons.BACK, onClick = onBack),
                center = LightTopBarCenter.Text(title),
                modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
            )

            val contentModifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(start = SETTINGS_START_UNITS.gridUnitsAsDp())

            if (scroll) {
                LightScrollView(modifier = contentModifier, content = content)
            } else {
                Column(modifier = contentModifier, content = content)
            }

            LightBottomBar(items = bottom)
        }
    }
}

/** Centres a short explanatory line in the space a list would have filled. */
@Composable
fun ColumnScope.SettingsEmptyMessage(text: String) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(end = ROW_END_UNITS.gridUnitsAsDp()),
        contentAlignment = Alignment.Center,
    ) {
        LightText(text = text, variant = LightTextVariant.Copy, lighten = true)
    }
}

/**
 * A row that either opens another screen or flips a switch. [checked] draws the
 * toggle glyph when it isn't null; leave it null for a row that navigates.
 * [subtitle] holds the current value, or a line explaining what the row does.
 */
@Composable
fun SettingsOptionRow(
    title: String,
    subtitle: String? = null,
    checked: Boolean? = null,
    nested: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .lightClickable(
                onClickLabel = title,
                role = if (checked == null) Role.Button else Role.Switch,
                onClick = onClick,
            )
            .padding(
                start = if (nested) NESTED_START_UNITS.gridUnitsAsDp() else 0f.gridUnitsAsDp(),
                top = STACKED_ROW_UNITS.gridUnitsAsDp(),
                end = ROW_END_UNITS.gridUnitsAsDp(),
                bottom = STACKED_ROW_UNITS.gridUnitsAsDp(),
            ),
        verticalAlignment = Alignment.Top,
    ) {
        if (checked != null) {
            Box(
                modifier = Modifier
                    .width(ICON_COLUMN_UNITS.gridUnitsAsDp())
                    .height((HEADING_PX * HEADING_LINE).designVerticalPxToDp()),
                contentAlignment = Alignment.CenterStart,
            ) {
                // The glyphs read as the action the tap performs, so an enabled
                // setting shows TOGGLE_OFF. Matches LightOS and the rest of Trinkets.
                LightIcon(
                    icon = if (checked) LightIcons.TOGGLE_OFF else LightIcons.TOGGLE_ON,
                    size = 2f,
                    contentDescription = null,
                )
            }
        }
        Column(modifier = Modifier.weight(1f)) {
            LightText(text = title, variant = LightTextVariant.Heading, maxLines = 2)
            if (!subtitle.isNullOrEmpty()) {
                LightText(text = subtitle, variant = LightTextVariant.Detail, lighten = true)
            }
        }
    }
}

/** One selectable option on a picker screen, marked with a filled circle. */
@Composable
fun SettingsChoiceRow(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(ROW_UNITS.gridUnitsAsDp())
            .lightClickable(onClickLabel = "Select $title", role = Role.RadioButton, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CheckCircle(checked = selected, size = 1.5f)
        Spacer(modifier = Modifier.width(1f.gridUnitsAsDp()))
        LightText(
            text = title,
            variant = LightTextVariant.Heading,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = ROW_END_UNITS.gridUnitsAsDp()),
        )
    }
}

/** A filled circle when [checked], an empty one otherwise. */
@Composable
fun CheckCircle(checked: Boolean, size: Float, modifier: Modifier = Modifier) {
    if (checked) {
        Icon(
            painter = painterResource(R.drawable.ic_check_circle_filled),
            contentDescription = null,
            tint = LightThemeTokens.colors.content,
            modifier = modifier.size(size.gridUnitsAsDp()),
        )
    } else {
        LightIcon(icon = LightIcons.CIRCLE, size = size, contentDescription = null, modifier = modifier)
    }
}

/** A small capitalised label dividing one group of settings from the next. */
@Composable
fun SettingsSectionHeader(text: String) {
    Spacer(modifier = Modifier.height(SECTION_GAP_UNITS.gridUnitsAsDp()))
    LightText(
        text = text,
        variant = LightTextVariant.Detail,
        lighten = true,
        modifier = Modifier.padding(end = ROW_END_UNITS.gridUnitsAsDp()),
    )
}

/**
 * A row that destroys something, dimmed to sit apart from the settings above
 * it. Kept to one line since the confirmation screen carries the warning.
 */
@Composable
fun SettingsDangerRow(title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(ROW_UNITS.gridUnitsAsDp())
            .lightClickable(onClickLabel = title, role = Role.Button, onClick = onClick)
            .padding(end = ROW_END_UNITS.gridUnitsAsDp()),
        verticalArrangement = Arrangement.Center,
    ) {
        LightText(
            text = title,
            variant = LightTextVariant.Heading,
            lighten = true,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
