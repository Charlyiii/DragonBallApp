package com.example.data.model.planet

import com.example.data.model.character.CharacterByIDResponseDTO
import com.example.data.model.character.CharacterListItemDTO
import com.example.data.model.character.toCharacterModel
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//TODO esto podría ir en la misma clase que la anterior
@Serializable
data class PlanetByIDResponseDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("isDestroyed") val isDestroyed: Boolean,
    @SerialName("description") val description: String,
    @SerialName("image") val image: String,
    @SerialName("characters") val characters: List<CharacterListItemDTO>? = null
    //Dragon Ball API URL
    //@SerialName ("deletedAt") val deletedAt: Long? = null
)

fun PlanetByIDResponseDTO.toPlanetModel(): PlanetModel {
    return PlanetModel(
        id = id,
        name = name,
        isDestroyed = isDestroyed,
        description = description,
        image = image,
        characterList = characters?.map { it.toCharacterModel() }
        //deletedAt = deletedAt
    )
}