package com.ferreiro.dragonballapp.domain.usecase.characters

import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.CharactersRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    operator fun invoke(): Flow<Either<List<CharacterModel>, ErrorModel>> {
        return charactersRepository.getCharacters()
    }
}