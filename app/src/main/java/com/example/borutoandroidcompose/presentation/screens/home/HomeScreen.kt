package com.example.borutoandroidcompose.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoandroidcompose.presentation.widgets.RatingWidget
import com.example.borutoandroidcompose.ui.theme.LARGE_PADDING
import com.example.borutoandroidcompose.viewModel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes().collectAsLazyPagingItems()
    Scaffold(
        topBar = { HomeTopBar(onSearchClicked = {}) }
    ) {
        RatingWidget(modifier = Modifier.padding(all = LARGE_PADDING).padding(top = it.calculateTopPadding()), rating = 3.6)
    }
}