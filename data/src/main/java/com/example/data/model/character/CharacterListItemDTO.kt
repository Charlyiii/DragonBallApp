package com.example.data.model.character

import com.example.data.common.extensions.toAffiliationEnum
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListItemDTO(
    @SerialName("affiliation") val affiliation: String,
    @SerialName("deletedAt") val deletedAt: Long?,
    @SerialName("description") val description: String,
    @SerialName("gender") val gender: String,
    @SerialName("id") val id: Int,
    @SerialName("image") val image: String,
    @SerialName("ki") val ki: String,
    @SerialName("maxKi") val maxKi: String,
    @SerialName("name") val name: String,
    @SerialName("race") val race: String
)

fun CharacterListItemDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id,
        image = image,
        race = race,
        gender = race,
        characterName = name,
        ki = ki,
        maxKi = maxKi,
        affiliation = affiliation.toAffiliationEnum(),
        description = description
    )
}

