package com.example.data.source

import com.example.data.model.character.CharacterByIDResponseDTO
import com.example.data.model.character.CharacterListItemDTO
import com.example.data.service.CharacterAPIService
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterDataSource @Inject constructor(
    private val characterAPIService: CharacterAPIService
) {
    suspend fun getCharactersList(): Either<List<CharacterListItemDTO>, ErrorModel> {

        return try {
            val response = characterAPIService.getCharacterList()
            if (response.isSuccessful) {
                val characterListDTO = response.body()?.characters
                if (characterListDTO.isNullOrEmpty()) {
                    Either.Error(ErrorModel.CommonError("Character list is null or empty"))
                } else {
                    Either.Success(characterListDTO)
                }
            } else {
                Either.Error(ErrorModel.CommonError("Error getting character list: ${response.code()}"))
            }
        } catch (e: IOException) {
            Either.Error(ErrorModel.CommonError("Network error"))
        } catch (e: HttpException) {
            Either.Error(ErrorModel.CommonError("HTTP error"))
        } catch (e: Exception) {
            Either.Error(ErrorModel.CommonError("Unexpected error"))
        }
    }

    suspend fun getCharacterByID(id: Int): Either<CharacterByIDResponseDTO, ErrorModel> {
        return try {
            val response = characterAPIService.getCharacterByID(id)
            return if (response.isSuccessful) {
                val characterDTO = response.body()
                if (characterDTO != null) {
                    Either.Success(characterDTO)
                } else {
                    Either.Error(ErrorModel.CommonError("Character not found"))
                }
            } else {
                Either.Error(ErrorModel.CommonError("Error getting character by id"))
            }
        } catch (e: IOException) {
            Either.Error(ErrorModel.CommonError("Network error"))
        } catch (e: HttpException) {
            Either.Error(ErrorModel.CommonError("HTTP error"))
        } catch (e: Exception) {
            Either.Error(ErrorModel.CommonError("Unexpected error"))
        }
    }
}