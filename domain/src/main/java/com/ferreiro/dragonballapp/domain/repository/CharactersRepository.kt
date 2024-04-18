package com.ferreiro.dragonballapp.domain.repository

import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<Either<List<CharacterModel>, ErrorModel>>
    fun getCharacterById(characterId: Int): Flow<Either<CharacterModel, ErrorModel>>
}