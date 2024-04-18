package com.ferreiro.dragonballapp.domain.model

data class PlanetModel(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String? = null,
)
