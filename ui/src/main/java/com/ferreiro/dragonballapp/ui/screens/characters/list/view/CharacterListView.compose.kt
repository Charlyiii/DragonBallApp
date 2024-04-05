package com.ferreiro.dragonballapp.ui.screens.characters.list.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.CharacterListItem

@Composable
fun CharacterListView(
    characterList: List<CharacterModel>,
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(characterList) { character ->
            CharacterListItem(
                character = character
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CharacterListViewPreview() {
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

