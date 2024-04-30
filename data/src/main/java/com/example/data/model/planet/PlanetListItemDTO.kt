package com.example.data.model.planet

import com.ferreiro.dragonballapp.domain.model.PlanetModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetListItemDTO(
   @SerialName ("id") val id: Int,
   @SerialName ("name") val name: String,
   @SerialName ("isDestroyed") val isDestroyed: Boolean,
   @SerialName ("description") val description: String,
   @SerialName ("image") val image: String,
   //Dragon Ball API URL
   //@SerialName ("deletedAt") val deletedAt: Long? = null
)

fun PlanetListItemDTO.toPlanetModel(): PlanetModel {
   return PlanetModel(
       id = id,
       name = name,
       isDestroyed = isDestroyed,
       description = description,
       image = image,
       //deletedAt = deletedAt
   )
}