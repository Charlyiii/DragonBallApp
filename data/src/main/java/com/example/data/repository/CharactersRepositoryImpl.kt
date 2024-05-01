package com.example.data.repository

import com.example.data.model.character.toCharacterModel
import com.example.data.source.CharacterDataSource
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.CharactersRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterDataSource: CharacterDataSource
) : CharactersRepository {
    override fun getCharacters(): Flow<Either<List<CharacterModel>, ErrorModel>> {
        return flow {
            when (val result = characterDataSource.getCharactersList()) {
                is Either.Success -> {
                    val characterModel = result.data.map { characterListItemDTO ->
                        characterListItemDTO.toCharacterModel()
                    }
                    emit(Either.Success(characterModel))
                }

                is Either.Error -> {
                    val errorModel = result.error
                    emit(Either.Error(errorModel))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getCharacterById(characterId: Int): Flow<Either<CharacterModel, ErrorModel>> {
        return flow {
            when (val result = characterDataSource.getCharacterByID(characterId)) {
                is Either.Success -> {
                    val characterModel = result.data.toCharacterModel()
                    emit(Either.Success(characterModel))
                }

                is Either.Error -> {
                    val errorModel = result.error
                    emit(Either.Error(errorModel))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
