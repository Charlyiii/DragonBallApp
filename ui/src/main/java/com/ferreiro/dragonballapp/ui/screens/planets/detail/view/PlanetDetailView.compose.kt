package com.ferreiro.dragonballapp.ui.screens.planets.detail.view

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.CarouselItemView
import com.ferreiro.dragonballapp.ui.theme.Typography

@Composable
fun PlanetDetailView(
    planet: PlanetModel,
    onClickCharacter: (CharacterModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlanetHeaderAndBody(
            planet = planet,
            modifier = Modifier.weight(4f)
        )
        PlanetDetailFooter(
            characters = planet.characterList,
            modifier = Modifier.weight(0.7f),
            onClickCharacter
            )
    }
}

//TODO refactorizar
@Composable
fun PlanetDetailHeader(planet: PlanetModel) {
    Row(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = planet.image,
            contentDescription = "${planet.name} image",
            error = painterResource(id = R.drawable.planets_img),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun PlanetDetailBody(planet: PlanetModel) {
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
                    //TODO Refactorizar texto en un componente
                    Row {
                        Text(
                            text = stringResource(R.string.destruido),
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            text = when (planet.isDestroyed) {
                                true -> stringResource(R.string.si)
                                false -> stringResource(R.string.no)
                            },
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }

                    Text(
                        text = stringResource(R.string.descripcion),
                        textAlign = TextAlign.Start,
                        style = Typography.titleMedium
                    )
                    Text(
                        text = planet.description,
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
fun PlanetHeaderAndBody(
    planet: PlanetModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        PlanetDetailHeader(planet)
        PlanetDetailBody(planet)
    }
}

@Composable
fun PlanetDetailFooter(
    characters: List<CharacterModel>?,
    modifier: Modifier,
    onClickCharacter: (CharacterModel) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.personajes),
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )

        characters?.takeIf { it.isNotEmpty() }?.let { list ->
            LazyRow(
                contentPadding = PaddingValues(start = 16.dp),
                modifier = Modifier.padding(bottom = 16.dp),
            ) {
                items(list) { character ->
                    CarouselItemView(
                        item = character,
                        onClick = onClickCharacter
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Este planeta no tiene personajes conocidos",
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlanetDetailViewPreview() {
    val planet =
        PlanetModel(
            id = 25,
            name = "Universo 11",
            description = "El Universo 11, es, como su nombre lo indica, el undécimo de los doce universos existentes actualmente en el mundo de Dragon Ball. Este es gemelo del Universo 2 y el lugar de origen de las Tropas del Orgullo. El Universo 11, es, como su nombre lo indica, el undécimo de los doce universos existentes actualmente en el mundo de Dragon Ball. Este es gemelo del Universo 2 y el lugar de origen de las Tropas del Orgullo.El Universo 11, es, como su nombre lo indica, el undécimo de los doce universos existentes actualmente en el mundo de Dragon Ball. Este es gemelo del Universo 2 y el lugar de origen de las Tropas del Orgullo.",
            image = "https://firebasestorage.googleapis.com/v0/b/dragonballappapi.appspot.com/o/planets%2FUniverso_11.webp?alt=media&token=32785ba8-e8fc-4f83-800f-c8c69d1f029e",
            isDestroyed = false,
            characterList = listOf(
                characterMock,
                characterMock,
                characterMock,
                characterMock,
                characterMock,
                characterMock,
                characterMock
            )
        )
    PlanetDetailView(planet)
}

val characterMock = CharacterModel(
    id = 0,
    image = "https://firebasestorage.googleapis.com/v0/b/dragonballappapi.appspot.com/o/Characters%2Fgoku1.webp?alt=media&token=a3c6c110-47f9-4286-8527-72717d440f45",
    race = "Saiyan",
    gender = "Masculino",
    characterName = "Goku",
    ki = "1000",
    maxKi = "9000",
    affiliation = Affiliation.Z_FIGHTER,
    description = "The strongest warrior in the universe"
)