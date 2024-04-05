package com.ferreiro.dragonballapp.ui.screens.characters.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.screens.characters.list.view.CharacterListView


class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    CharacterListView(
                        characterList = listOf(
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                            CharacterModel(
                                id = 0,
                                image = R.drawable.goku_preview,
                                race = "Saiyan",
                                gender = "Masculino",
                                characterName = "Goku",
                                ki = "1000",
                                maxKi = "9000",
                                affiliation = Affiliation.Z_FIGHTER,
                                description = "The strongest warrior in the universe",
                                originPlanet = PlanetModel(
                                    id = 0,
                                    name = "Earth",
                                    description = "The planet where Goku was born",
                                    isDestroyed = false
                                )
                            ),
                        )
                    )
                }
            }
        }
    }
}


