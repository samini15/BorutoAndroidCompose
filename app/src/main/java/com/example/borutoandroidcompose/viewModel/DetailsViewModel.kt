package com.example.borutoandroidcompose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.useCases.details.DetailsUseCases
import com.example.borutoandroidcompose.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCases: DetailsUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> = _selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(Constants.DETAILS_SCREEN_ARG_KEY)
            _selectedHero.value = heroId?.let { id ->
                detailsUseCases.findSelectedHeroUseCase(id)
            }
        }
    }
}