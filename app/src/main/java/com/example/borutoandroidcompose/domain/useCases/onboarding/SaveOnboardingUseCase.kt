package com.example.borutoandroidcompose.domain.useCases.onboarding

import com.example.borutoandroidcompose.domain.repository.BorutoRepository

class SaveOnboardingUseCase(
    private val repository: BorutoRepository
) {
    suspend operator fun invoke(completed: Boolean) =
        repository.saveOnboardingState(completed = completed)
}