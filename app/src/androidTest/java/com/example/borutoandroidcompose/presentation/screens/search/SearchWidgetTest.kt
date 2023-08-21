package com.example.borutoandroidcompose.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.borutoandroidcompose.utils.UISemantics
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChanged = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_TEXT_FIELD)
            .performTextInput("Shayan")
        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_TEXT_FIELD)
            .assertTextEquals("Shayan")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseIconOnce_assertEmptyInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChanged = {
                    text.value = it
                },
                onSearchClicked = {},
                onCloseClicked = {}
            )
        }
        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_TEXT_FIELD)
            .performTextInput("Shayan")

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_CLOSE_ICON)
            .performClick()

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_TEXT_FIELD)
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseIconTwice_assertCloseSearchBar() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChanged = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_TEXT_FIELD)
            .performTextInput("Shayan")

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_CLOSE_ICON)
            .performClick()
            .performClick()

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_WIDGET)
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseIconOnce_whenInputTextIsEmpty_assertCloseSearchBar() {
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChanged = {
                        text.value = it
                    },
                    onSearchClicked = {},
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_CLOSE_ICON)
            .performClick()

        composeTestRule.onNodeWithContentDescription(UISemantics.SEARCH_WIDGET)
            .assertDoesNotExist()
    }
}