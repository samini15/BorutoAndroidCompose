package com.example.borutoandroidcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoandroidcompose.presentation.screens.SplashScreen
import com.example.borutoandroidcompose.presentation.screens.details.DetailsScreen
import com.example.borutoandroidcompose.presentation.screens.home.HomeScreen
import com.example.borutoandroidcompose.presentation.screens.onboarding.OnboardingScreen
import com.example.borutoandroidcompose.presentation.screens.search.SearchScreen
import com.example.borutoandroidcompose.utils.Constants

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(Constants.DETAILS_SCREEN_ARG_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}