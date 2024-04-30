package com.example.data.service

import com.example.data.model.common.responseList.PlanetListResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface PlanetAPIService {
    @GET("planet_list.json")
    suspend fun getPlanetList(): Response<PlanetListResponseDTO>
}