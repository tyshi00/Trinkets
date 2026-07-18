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
import androidx.compose.ui.text.style.TextAlign
import com.thelightphone.sdk.SealedLightActivity
import com.thelightphone.sdk.SimpleLightScreen
import com.thelightphone.sdk.ui.LightBarButton
import com.thelightphone.sdk.ui.LightBottomBar
import com.thelightphone.sdk.ui.LightIcons
import com.thelightphone.sdk.ui.LightText
import com.thelightphone.sdk.ui.LightTextVariant
import com.thelightphone.sdk.ui.LightTheme
import com.thelightphone.sdk.ui.LightThemeController
import com.thelightphone.sdk.ui.LightThemeTokens
import com.thelightphone.sdk.ui.LightTopBar
import com.thelightphone.sdk.ui.LightTopBarCenter
import com.thelightphone.sdk.ui.gridUnitsAsDp

/**
 * Displays a single block of today's content: a title/heading, a body, and
 * an optional secondary line (e.g. a philosophy prompt's follow-up
 * question, or a morning prompt's intensity tag). Shared by Poem, Literary
 * Excerpt, Philosophy Prompt, and Morning Prompt. They're all "read one
 * thing today" screens that only differ in copy.
 */
class TextContentScreen(
    sealedActivity: SealedLightActivity,
    private val topBarTitle: String,
    private val heading: String,
    private val body: String,
    private val secondaryLine: String? = null,
    private val emptyMessage: String = "Nothing to show yet.",
) : SimpleLightScreen<Unit>(sealedActivity) {

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()

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
                    center = LightTopBarCenter.Text(topBarTitle),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                if (body.isBlank()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        contentAlignment = Alignment.Center,
                    ) {
                        LightText(
                            text = emptyMessage,
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
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (heading.isNotBlank()) {
                            LightText(text = heading, variant = LightTextVariant.Subheading, align = TextAlign.Center)
                            Spacer(modifier = Modifier.height(1f.gridUnitsAsDp()))
                        }
                        LightText(text = body, variant = LightTextVariant.ParagraphWide, align = TextAlign.Center)
                        if (!secondaryLine.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
                            LightText(
                                text = secondaryLine,
                                variant = LightTextVariant.Copy,
                                lighten = true,
                                align = TextAlign.Center,
                            )
                        }
                    }
                }

                LightBottomBar(items = listOf())
            }
        }
    }
}
