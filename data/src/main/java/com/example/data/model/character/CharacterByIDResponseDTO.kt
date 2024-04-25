package com.example.data.model.character

import com.example.data.common.extensions.toAffiliationEnum
import com.example.data.model.planet.PlanetDTO
import com.example.data.model.planet.toPlanetModel
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterByIDResponseDTO(
    @SerialName("affiliation") val affiliation: String,
    @SerialName("description") val description: String,
    @SerialName("gender") val gender: String,
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String,
    @SerialName("ki") val ki: String,
    @SerialName("maxKi") val maxKi: String,
    @SerialName("name") val name: String,
    @SerialName("race") val race: String,
    @SerialName("originPlanet") val originPlanet: PlanetDTO,
    @SerialName("transformations") val transformations: List<TransformationDTO>
)

fun CharacterByIDResponseDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id,
        image = image,
        race = race,
        gender = race,
        characterName = name,
        ki = ki,
        maxKi = maxKi,
        affiliation = affiliation.toAffiliationEnum(),
        description = description,
        originPlanet = originPlanet.toPlanetModel(),
        transformations = transformations.map { it.toTransformationModel() }
    )
}
