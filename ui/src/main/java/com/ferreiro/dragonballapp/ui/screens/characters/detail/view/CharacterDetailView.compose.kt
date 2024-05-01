package com.ferreiro.dragonballapp.ui.screens.characters.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.ui.common.components.CarouselItemView
import com.ferreiro.dragonballapp.ui.common.components.getCharacterGradient
import com.ferreiro.dragonballapp.ui.common.extensions.toReadableString
import com.ferreiro.dragonballapp.ui.theme.Typography

//TODO refactorizar diseño
@Composable
fun CharacterDetailView(
    character: CharacterModel,
    onClickPlanet: (PlanetModel) -> Unit = {},
    onClickTransformation: (TransformationModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(getCharacterGradient(character.affiliation))
    ) {
        CharacterHeaderAndBody(character = character, onClickPlanet)
        CharacterDetailFooter(
            transformationList = character.transformations,
            onClickTransformation = onClickTransformation
        )
    }
}

//TODO refactorizar
@Composable
fun CharacterDetailHeader(character: CharacterModel) {
    Row(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            model = character.image,
            contentDescription = "${character.characterName} image",
            error = painterResource(id = R.drawable.goku_preview)
        )
    }
}

@Composable
fun CharacterDetailBody(
    character: CharacterModel,
    onClickPlanet: (PlanetModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
    ) {
        Column(

        ) {
            Box {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .width(100.dp)
                            .clickable {
                                onClickPlanet(character.originPlanet!!)
                            },
                        model = character.originPlanet?.image,
                        contentDescription = "${character.originPlanet?.name} image",
                        error = painterResource(id = R.drawable.planets_img), //TODO R.drawable.no_image),
                        contentScale = ContentScale.Inside
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = character.originPlanet?.name ?: "Unknown",
                        textAlign = TextAlign.Center,
                        style = Typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(50.dp))

                    //TODO Refactorizar texto en un componente
                    Row {
                        Text(
                            //TODO: localizar strings
                            text = "KI: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = character.ki,
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                    Row {
                        Text(
                            //TODO: localizar strings
                            text = "Max KI: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = character.maxKi,
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                    Row {
                        Text(
                            //TODO: localizar strings
                            text = "Raza: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = character.race,
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                    Row {
                        Text(
                            //TODO: localizar strings
                            text = "Genero: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = character.gender,
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                    Row {
                        Text(
                            //TODO: localizar strings
                            text = "Faccion: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = character.affiliation.toReadableString(),
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                    Text(
                        //TODO: localizar strings
                        text = "Descripcion: ",
                        textAlign = TextAlign.Start,
                        style = Typography.titleMedium
                    )
                    Text(
                        text = character.description,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterHeaderAndBody(
    character: CharacterModel,
    onClickPlanet: (PlanetModel) -> Unit = {}
) {
    Column {
        CharacterDetailHeader(character)
        HorizontalDivider(modifier = Modifier.padding(16.dp))
        CharacterDetailBody(character, onClickPlanet)
    }
}

@Composable
fun CharacterDetailFooter(
    transformationList: List<TransformationModel>?,
    onClickTransformation: (TransformationModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .height(125.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            text = "Transformaciones",
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )

        transformationList?.takeIf { it.isNotEmpty() }?.let { list ->
            LazyRow(
                contentPadding = PaddingValues(start = 16.dp),
                modifier = Modifier.padding(bottom = 16.dp),
            ) {
                items(list) { transformation ->
                    CarouselItemView(
                        item = transformation,
                        onClick = onClickTransformation
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
            image = "https://dragonball-api.com/characters/goku_normal.webp", //TODO: Cambiar a URL de imagen,
            race = "Saiyan",
            gender = "Male",
            characterName = "Goku",
            ki = "60.000.000",
            maxKi = "90 Septillion",
            affiliation = Affiliation.Z_FIGHTER,
            description = "El protagonista de la serie, conocido por su gran poder y personalidad amigable. Originalmente enviado a la Tierra como un infante volador con la misión de conquistarla. Sin embargo, el caer por un barranco le proporcionó un brutal golpe que si bien casi lo mata, este alteró su memoria y anuló todos los instintos violentos de su especie, lo que lo hizo crecer con un corazón puro y bondadoso, pero conservando todos los poderes de su raza. No obstante, en la nueva continuidad de Dragon Ball se establece que él fue enviado por sus padres a la Tierra con el objetivo de sobrevivir a toda costa a la destrucción de su planeta por parte de Freeza. Más tarde, Kakarot, ahora conocido como Son Goku, se convertiría en el príncipe consorte del monte Fry-pan y líder de los Guerreros Z, así como el mayor defensor de la Tierra y del Universo 7, logrando mantenerlos a salvo de la destrucción en innumerables ocasiones, a pesar de no considerarse a sí mismo como un héroe o salvador.",
            originPlanet = PlanetModel(
                id = 0,
                name = "Tierra",
                description = "The planet where Goku was born",
                isDestroyed = false,
                image = "Espera a URL" //TODO: Cambiar a URL de imagen
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
