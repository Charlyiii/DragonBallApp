package com.example.data.common.extensions

import com.ferreiro.dragonballapp.domain.model.Affiliation

fun String.toAffiliationEnum(): Affiliation {
    return when (this) {
        "Z Fighter" -> Affiliation.Z_FIGHTER
        "Red Ribbon Army" -> Affiliation.RED_RIBBON_ARMY
        "Namekian Warrior" -> Affiliation.NAMEKIAN_WARRIOR
        "Freelancer" -> Affiliation.FREELANCER
        "Army of Frieza" -> Affiliation.ARMY_OF_FRIEZA
        "Pride Troopers" -> Affiliation.PRIDE_TROOPERS
        "Assistant of Vermoud" -> Affiliation.ASSISTANT_OF_VERMOUD
        "Assistant of Beerus" -> Affiliation.ASSISTANT_OF_BEERUS
        "God" -> Affiliation.GOD
        "Villain" -> Affiliation.VILLAIN
        else -> Affiliation.OTHER
    }
}