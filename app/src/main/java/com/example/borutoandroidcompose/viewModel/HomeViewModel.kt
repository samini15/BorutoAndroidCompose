package com.example.borutoandroidcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.useCases.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {

    fun getAllHeroes(): Flow<PagingData<Hero>> =
        homeUseCases.getAllHeroesUseCase()
}