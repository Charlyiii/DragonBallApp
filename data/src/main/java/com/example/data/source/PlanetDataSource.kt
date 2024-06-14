package com.example.data.source

import com.example.data.model.character.CharacterByIDResponseDTO
import com.example.data.model.planet.PlanetByIDResponseDTO
import com.example.data.model.planet.PlanetListItemDTO
import com.example.data.service.PlanetAPIService
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PlanetDataSource @Inject constructor(
    private val planetAPIService: PlanetAPIService
) {
    suspend fun getPlanetList(): Either<List<PlanetListItemDTO>, ErrorModel> {
        return try {
            val response = planetAPIService.getPlanetList()
            return if (response.isSuccessful) {
                val planetListDTO = response.body()?.planets
                if (planetListDTO.isNullOrEmpty()) {
                    Either.Error(ErrorModel.CommonError("Character list is null"))
                } else {
                    Either.Success(planetListDTO)
                }
            } else {
                Either.Error(ErrorModel.CommonError("Error getting planet list"))
            }
        } catch (e: IOException) {
            Either.Error(ErrorModel.CommonError("Network error"))
        } catch (e: HttpException) {
            Either.Error(ErrorModel.CommonError("HTTP error"))
        } catch (e: Exception) {
            Either.Error(ErrorModel.CommonError("Unexpected error"))
        }
    }

    suspend fun getPlanetByID(id: Int): Either<PlanetByIDResponseDTO, ErrorModel> {
        return try {
            val response = planetAPIService.getPlanetByID(id)
            return if (response.isSuccessful) {
                val planetDTO = response.body()
                if (planetDTO != null) {
                    Either.Success(planetDTO)
                } else {
                    Either.Error(ErrorModel.CommonError("Planet not found"))
                }
            } else {
                Either.Error(ErrorModel.CommonError("Error getting planet by id"))
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
