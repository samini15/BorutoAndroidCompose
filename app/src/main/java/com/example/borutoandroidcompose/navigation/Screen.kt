package com.example.borutoandroidcompose.navigation

sealed class Screen(val route: String) {
    object Splash: Screen(route = "splash_screen")
    object Onboarding: Screen(route = "onboarding_screen")
    object Home: Screen(route = "home_screen")
    object Details: Screen(route = "details_screen/{heroId}") {
        fun passHeroId(heroId: Int): String = "details_screen/$heroId"
    }
    object Search: Screen(route = "search_screen")
}
