package com.ferreiro.dragonballapp.domain.model

data class CharacterModel(
    val id: Int,
    val image: String,
    val race: String,
    val gender: String,
    val characterName: String,
    val ki: String,
    val maxKi: String,
    val affiliation: Affiliation,
    val description: String,
    val originPlanet: PlanetModel? = null,
    val transformations: List<TransformationModel>? = null,
    //Dragon Ball API URL
    val deletedAt: Long? = null
)

enum class Affiliation {
    Z_FIGHTER,
    RED_RIBBON_ARMY,
    NAMEKIAN_WARRIOR,
    FREELANCER,
    ARMY_OF_FRIEZA,
    PRIDE_TROOPERS,
    ASSISTANT_OF_VERMOUD,
    ASSISTANT_OF_BEERUS,
    GOD,
    VILLAIN,
    OTHER
}