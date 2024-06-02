package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.usecase.characters.GetCharacterListUseCase
import com.ferreiro.dragonballapp.domain.utils.Either
import com.ferreiro.dragonballapp.ui.common.extensions.toAffiliation
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

    private var originalList: List<CharacterModel> = listOf()

    // Listas de opciones
    private val _availableAffiliations = MutableStateFlow<List<Affiliation>>(emptyList())
    val availableAffiliations: StateFlow<List<Affiliation>> = _availableAffiliations

    private val _availableRaces = MutableStateFlow<List<String>>(emptyList())
    val availableRaces: StateFlow<List<String>> = _availableRaces

    private val _availableGenders = MutableStateFlow<List<String>>(emptyList())
    val availableGenders: StateFlow<List<String>> = _availableGenders

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            getCharacterListUseCase().collect { result ->
                when (result) {
                    is Either.Success -> {
                        originalList = result.data
                        extractFilters()
                        _stateFlow.value = CharacterListState.Success(characterList = originalList)
                    }

                    is Either.Error -> {
                        _stateFlow.value = CharacterListState.Error(error = result.error)
                    }
                }
            }
        }
    }
    fun sortAZ() {
        val sortedList = originalList.sortedBy { it.characterName }
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }

    fun sortZA() {
        val sortedList = originalList.sortedByDescending { it.characterName }
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }

    fun sortByAffiliation() {
        val sortedList = originalList.sortedBy { it.affiliation }
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }

    fun sortByRace() {
        val sortedList = originalList.sortedBy { it.race }
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }

    fun sortByGender() {
        val sortedList = originalList.sortedBy { it.gender }
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }

    fun filterByAffiliation(affiliation: String, context: Context) {
        val sortedList = originalList.filter { it.affiliation ==  affiliation.toAffiliation(context)}
        _stateFlow.value = CharacterListState.Success(characterList = sortedList)
    }
    fun filterByRace(race: String) {
        val filteredList = originalList.filter { it.race == race }
        _stateFlow.value = CharacterListState.Success(characterList = filteredList)
    }

    fun filterByGender(gender: String) {
        val filteredList = originalList.filter { it.gender == gender }
        _stateFlow.value = CharacterListState.Success(characterList = filteredList)
    }

    private fun extractFilters() {
        _availableAffiliations.value = originalList.map { it.affiliation }.distinct()
        _availableRaces.value = originalList.map { it.race }.distinct()
        _availableGenders.value = originalList.map { it.gender }.distinct()
    }
}

sealed interface CharacterListState {
    data class Success(val characterList: List<CharacterModel>) : CharacterListState
    data class Error(val error: ErrorModel) : CharacterListState
    data object Loading : CharacterListState
}
