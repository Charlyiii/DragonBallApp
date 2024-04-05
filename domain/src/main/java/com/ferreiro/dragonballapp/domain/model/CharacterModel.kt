package com.ferreiro.dragonballapp.domain.model

data class CharacterModel(
    val id: Int,
    val image: String,
    val characterName: String,
    val ki: String,
    val maxKi: String,
    val affiliation: String,
    val description: String,
    val originPlanet: PlanetModel,
    val transformations: List<TransformationModel>,
)
