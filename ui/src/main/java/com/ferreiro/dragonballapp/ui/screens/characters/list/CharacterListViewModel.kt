package com.ferreiro.dragonballapp.ui.screens.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.usecase.characters.GetCharacterListUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow<CharacterListState>(CharacterListState.Loading)
    val stateFlow: StateFlow<CharacterListState> = _stateFlow

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            getCharacterListUseCase().collect { result ->
                when (result) {
                    is Either.Success -> {
                        _stateFlow.value = CharacterListState.Success(characterList = result.data)
                    }

                    is Either.Error -> {
                        _stateFlow.value = CharacterListState.Error(error = result.error)
                    }
                }
            }
        }
    }

}
sealed interface CharacterListState {
    data class Success(val characterList: List<CharacterModel>) : CharacterListState
    data class Error(val error: ErrorModel) : CharacterListState
    data object Loading : CharacterListState
}