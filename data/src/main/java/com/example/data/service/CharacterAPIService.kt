package com.example.data.service

import com.example.data.model.character.CharacterByIDResponseDTO
import com.example.data.model.common.characterList.CharacterListResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterAPIService {

    @GET("characters?limit=300")
    suspend fun getCharacterList(): Response<CharacterListResponseDTO>

    @GET("characters/{id}")
    suspend fun getCharacterByID(@Path("id") id: Int): Response<CharacterByIDResponseDTO>
}
