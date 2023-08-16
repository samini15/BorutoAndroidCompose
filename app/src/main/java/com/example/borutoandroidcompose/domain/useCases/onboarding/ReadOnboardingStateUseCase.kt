package com.example.borutoandroidcompose.domain.useCases.onboarding

import com.example.borutoandroidcompose.domain.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingStateUseCase(
    private val repository: BorutoRepository
) {
    operator fun invoke(): Flow<Boolean> =
        repository.readOnboardingState()
}