package com.example.data.source

import com.example.data.model.character.CharacterByIDResponseDTO
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

    suspend fun getCharacterByID(id: Int): Either<CharacterByIDResponseDTO, ErrorModel>{
        val response = characterAPIService.getCharacterByID(id)
        return if (response.isSuccessful) {
            val characterDTO = response.body()
            if (characterDTO != null) {
                Either.Success(characterDTO)
            } else {
                Either.Error(ErrorModel.CommonError("Character not found"))
            }
        } else {
            Either.Error(ErrorModel.CommonError(response.message()))
        }
    }
}