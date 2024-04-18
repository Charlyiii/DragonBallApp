package com.ferreiro.dragonballapp.ui.screens.characters.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.usecase.characters.GetCharacterByIDUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIDUseCase: GetCharacterByIDUseCase
) : ViewModel() {
    private val _characterStateFlow = MutableStateFlow<CharacterState>(CharacterState.Loading)
    val characterStateFlow: StateFlow<CharacterState> = _characterStateFlow

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterByIDUseCase(id).collect { result ->
                when (result) {
                    is Either.Success -> _characterStateFlow.value =
                        CharacterState.Success(character = result.data)

                    is Either.Error -> _characterStateFlow.value =
                        CharacterState.Error(error = result.error)
                }
            }
        }
    }
}

sealed interface CharacterState {
    data class Success(val character: CharacterModel) : CharacterState
    data class Error(val error: ErrorModel) : CharacterState
    data object Loading : CharacterState
}