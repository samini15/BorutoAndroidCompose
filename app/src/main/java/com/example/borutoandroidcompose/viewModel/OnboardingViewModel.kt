package com.example.borutoandroidcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoandroidcompose.domain.useCases.onboarding.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val useCases: OnboardingUseCases
) : ViewModel() {
    fun saveOnboardingState(completed: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        useCases.saveOnboardingUseCase(completed = completed)
    }
}