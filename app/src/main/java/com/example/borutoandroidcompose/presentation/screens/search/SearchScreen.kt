package com.example.borutoandroidcompose.presentation.screens.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoandroidcompose.presentation.common.ListContent
import com.example.borutoandroidcompose.ui.theme.topAppBarBackgroundColor
import com.example.borutoandroidcompose.viewModel.SearchViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    // ViewModel Observation
    val searchQuery by searchViewModel.searchQuery
    val items = searchViewModel.searchedItems.collectAsLazyPagingItems()

    // Adapt status bar color
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    )

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChanged = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchItems(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        ListContent(navController = navController, items = items, topPadding = it.calculateTopPadding())
    }
}