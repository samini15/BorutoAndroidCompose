package com.example.borutoandroidcompose.presentation.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.ui.theme.Purple40
import com.example.borutoandroidcompose.ui.theme.Purple80

@Composable
fun SplashScreen(navController: NavHostController) {
    val logoRotationDegrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        logoRotationDegrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
    }
    Splash(rotationDegrees = logoRotationDegrees.value)
}

@Composable
fun Splash(rotationDegrees: Float) {
    if (isSystemInDarkTheme()) {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Color.Black, Color.DarkGray)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier.rotate(degrees = rotationDegrees)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(Purple40, Purple80)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(R.string.app_logo),
                modifier = Modifier.rotate(degrees = rotationDegrees)
            )
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
private fun SplashScreenDarkThemePreview() {
    Splash(rotationDegrees = 0f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_NO)
private fun SplashScreenLightThemePreview() {
    Splash(rotationDegrees = 0f)
}