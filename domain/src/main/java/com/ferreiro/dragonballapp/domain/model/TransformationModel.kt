package com.ferreiro.dragonballapp.domain.model

import java.io.Serializable

data class TransformationModel(
    val id: Int,
    val name: String,
    val image: String,
    val ki: String,
    //Dragon Ball API URL
    //val deletedAt: Long? = null
) : Serializable //Could implement Parcelable (con Parcelize, mirar como añadir el plugin)
