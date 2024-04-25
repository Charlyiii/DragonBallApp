package com.example.data.model.character

import com.ferreiro.dragonballapp.domain.model.TransformationModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransformationDTO(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("ki") val ki: String,
)

fun TransformationDTO.toTransformationModel(): TransformationModel {
    return TransformationModel(
        id = id,
        name = name,
        image = image,
        ki = ki
    )
}
