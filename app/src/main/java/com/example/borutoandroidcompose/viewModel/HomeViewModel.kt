package com.example.borutoandroidcompose.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.useCases.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing = _isRefreshing

    fun getAllHeroes(): Flow<PagingData<Hero>> =
        homeUseCases.getAllHeroesUseCase()

    fun updateRefreshState(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }

    fun refresh(items: LazyPagingItems<Hero>?) {
        _isRefreshing.value = true
        items?.refresh()
        _isRefreshing.value = false
    }
}