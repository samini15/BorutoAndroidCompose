package com.example.borutoandroidcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoandroidcompose.domain.useCases.onboarding.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: OnboardingUseCases
) : ViewModel() {
    private val _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted: StateFlow<Boolean> = _onboardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onboardingCompleted.value = useCases.readOnboardingStateUseCase().stateIn(viewModelScope).value
        }
    }
}