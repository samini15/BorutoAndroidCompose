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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    // SharedFlow used to trigger only ONE time this event
    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent: SharedFlow<UIEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(emptyMap())
    val colorPalette: State<Map<String, String>> = _colorPalette

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

    fun generateColorPalette() =
        viewModelScope.launch {
            _uiEvent.emit(UIEvent.GenerateColorPalette)
        }
}

sealed class UIEvent {
    object GenerateColorPalette: UIEvent()
}