package com.example.borutoandroidcompose.domain.useCases.details

import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.repository.BorutoRepository

class FindSelectedHeroUseCase(
    private val repository: BorutoRepository
) {
    suspend operator fun invoke(id: Int): Hero = repository.findSelectedHero(id = id)
}