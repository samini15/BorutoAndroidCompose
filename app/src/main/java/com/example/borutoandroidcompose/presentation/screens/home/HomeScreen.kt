package com.example.borutoandroidcompose.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoandroidcompose.presentation.common.ListContent
import com.example.borutoandroidcompose.viewModel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes().collectAsLazyPagingItems()
    Scaffold(
        topBar = { HomeTopBar(onSearchClicked = {}) }
    ) {
        ListContent(items = allHeroes, navController = navController, topPadding = it.calculateTopPadding())
    }
}