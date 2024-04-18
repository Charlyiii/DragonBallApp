package com.ferreiro.dragonballapp.domain.usecase.characters

import com.ferreiro.dragonballapp.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterByIDUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    operator fun invoke(id: Int) = charactersRepository.getCharacterById(id)
}