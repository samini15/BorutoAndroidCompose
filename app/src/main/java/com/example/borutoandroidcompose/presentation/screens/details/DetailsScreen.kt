package com.example.borutoandroidcompose.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.borutoandroidcompose.viewModel.DetailsViewModel

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsStateWithLifecycle()

    DetailsContent(navController = navController, selectedHero = selectedHero)
}