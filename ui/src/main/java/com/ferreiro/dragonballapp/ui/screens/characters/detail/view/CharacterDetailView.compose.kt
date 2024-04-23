package com.ferreiro.dragonballapp.ui.screens.characters.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.ui.common.components.TransformationItemView
import com.ferreiro.dragonballapp.ui.theme.Typography


@Composable
fun CharacterDetailView(
    character: CharacterModel,
) {
    Column {
        CharacterDetailHeader(character)
        CharacterDetailBody(character)
        CharacterDetailFooter(character.transformations)
    }
}

@Composable
fun ColumnScope.CharacterDetailHeader(character: CharacterModel) {
    Box(
        modifier = Modifier
            .weight(2f)
            .fillMaxWidth()
            .background(color = Color.Yellow)
    ) {
        Text(
            text = character.characterName,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun ColumnScope.CharacterDetailBody(character: CharacterModel) {
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(0.7f)
            .fillMaxWidth()
    ) {
        Text(
            text = character.description,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Composable
fun ColumnScope.CharacterDetailFooter(transformationList: List<TransformationModel>?) {
    Column(
        modifier = Modifier
            .weight(0.5f)
            .background(color = Color.White)
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            text = "Transformations",
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )

        transformationList?.takeIf { it.isNotEmpty() }?.let { list ->
            LazyRow(
                contentPadding = PaddingValues(start = 16.dp),
                modifier = Modifier.padding(bottom = 16.dp),
            ) {
                items(list) { transformation ->
                    TransformationItemView(
                        transformation = transformation
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            text = "No transformations available",
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CharacterDetailViewPreview() {
    CharacterDetailView(
        character = CharacterModel(
            id = 0,
            image = R.drawable.goku_preview.toString(),
            race = "Saiyan",
            gender = "Masculino",
            characterName = "Goku",
            ki = "1000",
            maxKi = "9000",
            affiliation = Affiliation.Z_FIGHTER,
            description = "El protagonista de la serie, conocido por su gran poder y personalidad amigable. Originalmente enviado a la Tierra como un infante volador con la misión de conquistarla. Sin embargo, el caer por un barranco le proporcionó un brutal golpe que si bien casi lo mata, este alteró su memoria y anuló todos los instintos violentos de su especie, lo que lo hizo crecer con un corazón puro y bondadoso, pero conservando todos los poderes de su raza. No obstante, en la nueva continuidad de Dragon Ball se establece que él fue enviado por sus padres a la Tierra con el objetivo de sobrevivir a toda costa a la destrucción de su planeta por parte de Freeza. Más tarde, Kakarot, ahora conocido como Son Goku, se convertiría en el príncipe consorte del monte Fry-pan y líder de los Guerreros Z, así como el mayor defensor de la Tierra y del Universo 7, logrando mantenerlos a salvo de la destrucción en innumerables ocasiones, a pesar de no considerarse a sí mismo como un héroe o salvador.",
            originPlanet = PlanetModel(
                id = 0,
                name = "Earth",
                description = "The planet where Goku was born",
                isDestroyed = false
            ),
            transformations = listOf(
                TransformationModel(
                    id = 0,
                    name = "Super Saiyan",
                    image = "",
                    ki = "1000"
                ),
                TransformationModel(
                    id = 1,
                    name = "Super Saiyan 2",
                    image = "",
                    ki = "2000"
                ),
                TransformationModel(
                    id = 2,
                    name = "Super Saiyan 3",
                    image = "",
                    ki = "3000"
                ),
                TransformationModel(
                    id = 3,
                    name = "Super Saiyan God",
                    image = "",
                    ki = "4000"
                )
            )
        )
    )
}

