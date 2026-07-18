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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class HistoryScreen(
    sealedActivity: SealedLightActivity,
) : SimpleLightScreen<Unit>(sealedActivity) {

    @Composable
    override fun Content() {
        val themeColors by LightThemeController.colors.collectAsState()
        val today = LocalDate.now()
        val todayLabel = "${today.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${today.dayOfMonth}"
        val facts = ContentRepository.historyFactsForToday()

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
                    center = LightTopBarCenter.Text("Today in History"),
                    modifier = Modifier.padding(bottom = 1f.gridUnitsAsDp()),
                )

                if (facts.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(horizontal = 1f.gridUnitsAsDp()),
                        contentAlignment = Alignment.Center,
                    ) {
                        LightText(
                            text = "No history facts logged for $todayLabel yet.",
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
                        LightText(text = todayLabel, variant = LightTextVariant.Subheading, align = TextAlign.Center)
                        Spacer(modifier = Modifier.height(1f.gridUnitsAsDp()))

                        facts.forEachIndexed { index, fact ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                LightText(
                                    text = "${fact.year}, ${fact.region}",
                                    variant = LightTextVariant.Detail,
                                    lighten = true,
                                    align = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(0.5f.gridUnitsAsDp()))
                                LightText(
                                    text = fact.event,
                                    variant = LightTextVariant.Paragraph,
                                    align = TextAlign.Center,
                                )
                            }
                            if (index != facts.lastIndex) {
                                Spacer(modifier = Modifier.height(1.5f.gridUnitsAsDp()))
                            }
                        }
                    }
                }

                LightBottomBar(items = listOf())
            }
        }
    }
}
