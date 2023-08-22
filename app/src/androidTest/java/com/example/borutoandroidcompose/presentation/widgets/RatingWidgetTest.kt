package com.example.borutoandroidcompose.presentation.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.example.borutoandroidcompose.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passZeroValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 0.0)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(0)
    }

    @Test
    fun passFiveValue_Assert_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 5.0)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(5)
    }

    @Test
    fun passZeroPointFiveValue_Assert_OneHalfStarAndFourEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 0.5)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(4)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(0)
    }

    @Test
    fun passOnePointFourValue_Assert_OneFilledStar_OneHalfStar_ThreeEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 1.4)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(3)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(1)
    }

    @Test
    fun passOnePointSixValue_Assert_TwoFilledStar_ThreeEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 1.6)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(3)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(2)
    }

    @Test
    fun passFourPointZeroValue_Assert_FourFilledStar_OneEmptyStar() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 4.0)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(1)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(4)
    }

    @Test
    fun passNegativeValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = -7.2)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(0)
    }

    @Test
    fun passInvalidValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(SMALL_PADDING), rating = 5.4)
        }

        composeTestRule.onAllNodesWithContentDescription(EMPTY_STARS)
            .assertCountEquals(5)

        composeTestRule.onAllNodesWithContentDescription(HALF_FILLED_STARS)
            .assertCountEquals(0)

        composeTestRule.onAllNodesWithContentDescription(FILLED_STARS)
            .assertCountEquals(0)
    }
}