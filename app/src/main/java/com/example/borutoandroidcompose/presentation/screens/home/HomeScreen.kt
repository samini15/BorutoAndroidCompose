package com.example.borutoandroidcompose.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoandroidcompose.navigation.Screen
import com.example.borutoandroidcompose.presentation.common.ListContent
import com.example.borutoandroidcompose.ui.theme.topAppBarBackgroundColor
import com.example.borutoandroidcompose.viewModel.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes().collectAsLazyPagingItems()

    // Adapt status bar color
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    )

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
    ) {
        ListContent(items = allHeroes, navController = navController, topPadding = it.calculateTopPadding())
    }
}