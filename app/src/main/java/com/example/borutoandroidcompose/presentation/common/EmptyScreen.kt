package com.example.borutoandroidcompose.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.ui.theme.DarkGray
import com.example.borutoandroidcompose.ui.theme.LightGray
import com.example.borutoandroidcompose.ui.theme.SMALL_PADDING
import com.example.borutoandroidcompose.viewModel.HomeViewModel
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    message: String = "Find your favorite heroes!",
    error: LoadState.Error? = null,
    items: LazyPagingItems<Hero>? = null
) {
    var message by remember {
        mutableStateOf(message)
    }

    var icon by remember {
        mutableStateOf(R.drawable.ic_search_placeholder)
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ), label = "animation"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, icon = icon, message = message, items = items)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmptyContent(
    viewModel: HomeViewModel = hiltViewModel(),
    alphaAnim: Float,
    icon: Int,
    message: String,
    items: LazyPagingItems<Hero>? = null,

) {
    // Swipe to refresh
    val isRefreshing by viewModel.isRefreshing
    var pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            viewModel.refresh(items = items)
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(alignment = Alignment.CenterHorizontally))
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon_content_description),
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
    }
}

private fun parseErrorMessage(error: LoadState.Error) : String {
    return when(error.error) {
        is SocketTimeoutException -> "Server Unavailable."
        is ConnectException -> "Internet Unavailable."
        else -> "Unknown Error."
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    EmptyContent(alphaAnim = ContentAlpha.disabled, icon = R.drawable.ic_network_error, message = "Internet Unavailable.")
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenDarkPreview() {
    EmptyContent(alphaAnim = ContentAlpha.disabled, icon = R.drawable.ic_network_error, message = "Internet Unavailable.")
}