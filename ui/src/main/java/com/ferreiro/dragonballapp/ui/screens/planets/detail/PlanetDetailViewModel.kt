package com.ferreiro.dragonballapp.ui.screens.planets.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.usecase.characters.GetCharacterByIDUseCase
import com.ferreiro.dragonballapp.domain.usecase.planets.GetPlanetByIDUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val getPlanetByIDUseCase: GetPlanetByIDUseCase
) : ViewModel() {
    private val _planetStateFlow = MutableStateFlow<PlanetState>(PlanetState.Loading)
    val planetStateFlow: StateFlow<PlanetState> = _planetStateFlow

    fun getPlanetById(id: Int) {
        viewModelScope.launch {
            getPlanetByIDUseCase(id).collect { result ->
                when (result) {
                    is Either.Success -> _planetStateFlow.value =
                        PlanetState.Success(planet = result.data)

                    is Either.Error -> _planetStateFlow.value =
                        PlanetState.Error(error = result.error)
                }
            }
        }
    }
}

sealed interface PlanetState {
    data class Success(val planet: PlanetModel) : PlanetState
    data class Error(val error: ErrorModel) : PlanetState
    data object Loading : PlanetState
}