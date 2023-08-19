package com.example.borutoandroidcompose.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.useCases.search.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedItems = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchedItems = _searchedItems

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchItems(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUseCases.searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect {
            _searchedItems.value = it
        }
    }
}