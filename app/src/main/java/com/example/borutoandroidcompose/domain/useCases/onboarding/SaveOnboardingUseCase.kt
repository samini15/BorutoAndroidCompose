package com.example.borutoandroidcompose.domain.useCases.onboarding

import com.example.borutoandroidcompose.domain.repository.local.LocalBorutoRepository

class SaveOnboardingUseCase(
    private val repository: LocalBorutoRepository
) {
    suspend operator fun invoke(completed: Boolean) =
        repository.saveOnboardingState(completed = completed)
}