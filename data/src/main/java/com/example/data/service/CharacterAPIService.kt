package com.example.data.service

import com.example.data.model.character.CharacterByIDResponseDTO
import com.example.data.model.common.characterList.CharacterListResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterAPIService {

    @GET("character_list.json")
    suspend fun getCharacterList(): Response<CharacterListResponseDTO>

    @GET("character/{characterId}.json")
    suspend fun getCharacterByID(@Path("characterId") id: Int): Response<CharacterByIDResponseDTO>
}
