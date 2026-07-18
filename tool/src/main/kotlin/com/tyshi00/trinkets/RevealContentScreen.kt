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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.thelightphone.sdk.ui.lightClickable

/**
 * A prompt with a hidden answer, used for the Joke of the Day (setup /
 * punchline) and Trivia (question / answer). Tapping anywhere reveals it.
 */
class RevealContentScreen(
    sealedActivity: SealedLightActivity,
    private val topBarTitle: String,
    private val prompt: String,
    private val answer: String,
    private val revealLabel: String = "TAP TO REVEAL",
    private val emptyMessage: String = "Nothing to show yet.",
) : SimpleLightScreen<Unit>(sealedActivity) {

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        var revealed by remember { mutableStateOf(false) }

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

                if (prompt.isBlank()) {
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
                            .padding(horizontal = 1f.gridUnitsAsDp())
                            .lightClickable { revealed = true },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LightText(text = prompt, variant = LightTextVariant.ParagraphWide, align = TextAlign.Center)
                        Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
                        if (revealed) {
                            LightText(
                                text = answer,
                                variant = LightTextVariant.ParagraphWide,
                                lighten = true,
                                align = TextAlign.Center,
                            )
                        } else {
                            LightText(
                                text = revealLabel,
                                variant = LightTextVariant.Detail,
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
