package com.example.data.model.common.responseList

import com.example.data.model.planet.PlanetListItemDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetListResponseDTO(
    @SerialName("items") val planets: List<PlanetListItemDTO>,
    val links: Links,
    val meta: Meta
)