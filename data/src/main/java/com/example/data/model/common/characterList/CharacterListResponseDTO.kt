package com.example.data.model.common.characterList

import com.example.data.model.character.CharacterListItemDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterListResponseDTO(
    @SerialName("items") val characters: List<CharacterListItemDTO>,
    val links: Links,
    val meta: Meta
)