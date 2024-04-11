package com.example.data.service

import com.example.data.model.common.CharacterListResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface CharacterAPIService {

    @GET("characters?limit=300")
    suspend fun getCharacterList(): Response<CharacterListResponseDTO>
}