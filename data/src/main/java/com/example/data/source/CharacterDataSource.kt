package com.example.data.source

import com.example.data.model.character.CharacterListItemDTO
import com.example.data.service.CharacterAPIService
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val characterAPIService: CharacterAPIService
){
    suspend fun getCharactersList(): Either<List<CharacterListItemDTO>, ErrorModel>{
        val response = characterAPIService.getCharacterList()
        return if (response.isSuccessful) {
            val characterListDTO = response.body()?.characters
            if (characterListDTO.isNullOrEmpty()) {
                Either.Error(ErrorModel.CommonError("Character list is null"))
            } else {
                Either.Success(characterListDTO)
            }
        } else {
            Either.Error(ErrorModel.CommonError(response.message()))
        }
    }
}