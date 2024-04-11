package com.ferreiro.dragonballapp.ui.common.extensions

import com.ferreiro.dragonballapp.domain.model.Affiliation

fun Affiliation.toReadableString(): String {
    return when (this) {
        //TODO Translate strings
        Affiliation.Z_FIGHTER -> "Z Fighter"
        Affiliation.RED_RIBBON_ARMY -> "Red Ribbon Army"
        Affiliation.NAMEKIAN_WARRIOR -> "Namekian Warrior"
        Affiliation.FREELANCER -> "Freelancer"
        Affiliation.ARMY_OF_FRIEZA -> "Army of Frieza"
        Affiliation.PRIDE_TROOPERS -> "Pride Troopers"
        Affiliation.ASSISTANT_OF_VERMOUD -> "Assistant of Vermoud"
        Affiliation.ASSISTANT_OF_BEERUS -> "Assistant of Beerus"
        Affiliation.GOD -> "God"
        Affiliation.VILLAIN -> "Villain"
        Affiliation.OTHER -> "Other"
    }
}
