package com.example.borutoandroidcompose.domain.useCases.onboarding

import com.example.borutoandroidcompose.domain.repository.local.LocalBorutoRepository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingStateUseCase(
    private val repository: LocalBorutoRepository
) {
    operator fun invoke(): Flow<Boolean> =
        repository.readOnboardingState()
}