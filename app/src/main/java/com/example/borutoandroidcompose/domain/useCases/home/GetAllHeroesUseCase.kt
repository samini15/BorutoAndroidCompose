package com.example.borutoandroidcompose.domain.useCases.home

import androidx.paging.PagingData
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: BorutoRepository
) {
    operator fun invoke(): Flow<PagingData<Hero>> = repository.getAllData()
}