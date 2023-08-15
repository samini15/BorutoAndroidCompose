package com.example.borutoandroidcompose.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.ui.theme.topAppBarBackgroundColor
import com.example.borutoandroidcompose.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Explore", color = MaterialTheme.colorScheme.topAppBarContentColor) },
        backgroundColor = MaterialTheme.colorScheme.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon_content_description),
                    tint = MaterialTheme.colorScheme.topAppBarContentColor
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(onSearchClicked = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeTopBarPreviewDark() {
    HomeTopBar(onSearchClicked = {})
}