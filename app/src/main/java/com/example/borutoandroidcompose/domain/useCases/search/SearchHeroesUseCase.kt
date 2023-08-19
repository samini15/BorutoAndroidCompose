package com.example.borutoandroidcompose.domain.useCases.search

import androidx.paging.PagingData
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: BorutoRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> =
        repository.searchHeroes(query = query)
}