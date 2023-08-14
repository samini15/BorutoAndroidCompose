package com.example.borutoandroidcompose.presentation.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.domain.model.OnboardingPage
import com.example.borutoandroidcompose.ui.theme.LARGEST_PADDING
import com.example.borutoandroidcompose.ui.theme.MEDIUM_PADDING
import com.example.borutoandroidcompose.ui.theme.PAGER_INDICATOR_SPACING
import com.example.borutoandroidcompose.ui.theme.PAGER_INDICATOR_WIDTH
import com.example.borutoandroidcompose.ui.theme.activeIndicatorColor
import com.example.borutoandroidcompose.ui.theme.inactiveIndicatorColor
import com.example.borutoandroidcompose.ui.theme.normalTextColor
import com.example.borutoandroidcompose.ui.theme.titleColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pages = listOf(
        OnboardingPage.Greetings,
        OnboardingPage.Explore,
        OnboardingPage.Power
    )

    val pagerState = com.google.accompanist.pager.rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            count = pages.size,
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { pageNumber ->
            PagerScreen(onboardingPage = pages[pageNumber])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterHorizontally),
            activeColor = MaterialTheme.colorScheme.activeIndicatorColor,
            inactiveColor = MaterialTheme.colorScheme.inactiveIndicatorColor,
            indicatorWidth = PAGER_INDICATOR_WIDTH,
            spacing = PAGER_INDICATOR_SPACING,
            indicatorShape = CircleShape
        )

        // Manually Pager indicator
        /*Row(
            Modifier
                .height(50.dp)
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            repeat(pages.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.DarkGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)

                )
            }
        }*/
        
        FinishButton(modifier = Modifier.weight(2f), pagerState = pagerState) {
            
        }
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onboardingPage.image),
            contentDescription = stringResource(R.string.onboarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onboardingPage.title,
            color = MaterialTheme.colorScheme.titleColor,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LARGEST_PADDING)
                .padding(top = MEDIUM_PADDING),
            text = onboardingPage.description,
            color = MaterialTheme.colorScheme.normalTextColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .padding(horizontal = LARGEST_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(modifier = modifier.padding(LARGEST_PADDING), visible = pagerState.currentPage == 2) {
            Button(onClick = { onClick }) {
                Text(text = "Finish")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun GreetingScreenPreview() {
    PagerScreen(onboardingPage = OnboardingPage.Greetings)
}

@Composable
@Preview(showBackground = true)
private fun ExploreScreenPreview() {
    PagerScreen(onboardingPage = OnboardingPage.Explore)
}

@Composable
@Preview(showBackground = true)
private fun PowerScreenPreview() {
    PagerScreen(onboardingPage = OnboardingPage.Power)
}