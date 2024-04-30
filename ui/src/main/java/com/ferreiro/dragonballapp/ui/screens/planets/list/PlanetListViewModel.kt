package com.ferreiro.dragonballapp.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.usecase.planets.GetPlanetListUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val getPlanetListUseCase: GetPlanetListUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow<PlanetListState>(PlanetListState.Loading)
    val stateFlow: StateFlow<PlanetListState> = _stateFlow

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            getPlanetListUseCase().collect { result ->
                when (result) {
                    is Either.Success -> {
                        _stateFlow.value = PlanetListState.Success(planetList = result.data)
                    }

                    is Either.Error -> {
                        _stateFlow.value = PlanetListState.Error(error = result.error)
                    }
                }
            }
        }
    }
}

sealed interface PlanetListState {
    data class Success(val planetList: List<PlanetModel>) : PlanetListState
    data class Error(val error: ErrorModel) : PlanetListState
    data object Loading : PlanetListState
}