package com.ferreiro.dragonballapp.ui.common.extensions

import android.content.Context
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation

fun Affiliation.toReadableString(context: Context): String {
    return when (this) {
        Affiliation.Z_FIGHTER -> context.getString(R.string.z_fighter)
        Affiliation.RED_RIBBON_ARMY ->context.getString(R.string.red_ribbon_army)
        Affiliation.NAMEKIAN_WARRIOR -> context.getString(R.string.namekian_warrior)
        Affiliation.FREELANCER -> context.getString(R.string.freelancer)
        Affiliation.ARMY_OF_FRIEZA -> context.getString(R.string.army_of_frieza)
        Affiliation.PRIDE_TROOPERS -> context.getString(R.string.pride_troopers)
        Affiliation.ASSISTANT_OF_VERMOUD -> context.getString(R.string.assistant_of_vermoud)
        Affiliation.ASSISTANT_OF_BEERUS -> context.getString(R.string.assistant_of_beerus)
        Affiliation.GOD -> context.getString(R.string.god)
        Affiliation.VILLAIN -> context.getString(R.string.villain)
        Affiliation.OTHER -> context.getString(R.string.other)
    }
}

fun String.toAffiliation(context: Context): Affiliation {
    return when (this) {
        context.getString(R.string.z_fighter) -> Affiliation.Z_FIGHTER
        context.getString(R.string.red_ribbon_army) -> Affiliation.RED_RIBBON_ARMY
        context.getString(R.string.namekian_warrior) -> Affiliation.NAMEKIAN_WARRIOR
        context.getString(R.string.freelancer) -> Affiliation.FREELANCER
        context.getString(R.string.army_of_frieza) -> Affiliation.ARMY_OF_FRIEZA
        context.getString(R.string.pride_troopers) -> Affiliation.PRIDE_TROOPERS
        context.getString(R.string.assistant_of_vermoud) -> Affiliation.ASSISTANT_OF_VERMOUD
        context.getString(R.string.assistant_of_beerus) -> Affiliation.ASSISTANT_OF_BEERUS
        context.getString(R.string.god) -> Affiliation.GOD
        context.getString(R.string.villain) -> Affiliation.VILLAIN
        context.getString(R.string.other) -> Affiliation.OTHER
        else -> {
            throw IllegalArgumentException("Invalid affiliation")}
    }
}
