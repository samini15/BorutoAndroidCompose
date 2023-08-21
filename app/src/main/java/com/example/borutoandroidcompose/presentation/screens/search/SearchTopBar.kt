package com.example.borutoandroidcompose.presentation.screens.search

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.borutoandroidcompose.ui.theme.topAppBarBackgroundColor
import com.example.borutoandroidcompose.ui.theme.topAppBarContentColor
import com.example.borutoandroidcompose.utils.Constants
import com.example.borutoandroidcompose.utils.UISemantics

@Composable
fun SearchTopBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChanged = onTextChanged,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@Composable
fun SearchWidget(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(TOP_APP_BAR_HEIGHT)
        .semantics { contentDescription = UISemantics.SEARCH_WIDGET },
        shadowElevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = UISemantics.SEARCH_TEXT_FIELD },
            value = text,
            onValueChange = { onTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = MaterialTheme.colorScheme.topAppBarContentColor,
                    maxLines = 1,
                    style = TextStyle(color = MaterialTheme.colorScheme.topAppBarContentColor)
                )
            },
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon_content_description),
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier
                        .semantics {
                                   contentDescription = UISemantics.SEARCH_CLOSE_ICON
                        },
                    onClick = {
                    if (text.isNotEmpty()) {
                        onTextChanged("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon_content_description),
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                    keyboardController?.hide()
                }
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.topAppBarContentColor)
        )
    }
}

@Preview
@Composable
fun SearchWidgetPreview() {
    SearchWidget(text = "", onTextChanged = {}, onSearchClicked = {}, onCloseClicked = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchWidgetDarkPreview() {
    SearchWidget(text = "", onTextChanged = {}, onSearchClicked = {}, onCloseClicked = {})
}