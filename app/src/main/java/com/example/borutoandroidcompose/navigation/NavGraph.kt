package com.example.borutoandroidcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoandroidcompose.presentation.screens.SplashScreen
import com.example.borutoandroidcompose.utils.Constants

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Onboarding.route) {

        }
        composable(route = Screen.Home.route) {

        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Constants.DETAILS_SCREEN_ARG_KEY) {
                type = NavType.IntType
            })
        ) {

        }
        composable(route = Screen.Search.route) {

        }
    }
}