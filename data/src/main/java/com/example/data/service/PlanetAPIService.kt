package com.example.data.service

import com.example.data.model.common.responseList.PlanetListResponseDTO
import com.example.data.model.planet.PlanetByIDResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetAPIService {
    @GET("planet_list.json")
    suspend fun getPlanetList(): Response<PlanetListResponseDTO>

    @GET("planet/{planetId}.json")
    suspend fun getPlanetByID(@Path("planetId") id: Int): Response<PlanetByIDResponseDTO>
}