package com.ferreiro.dragonballapp.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.extensions.toReadableString
import com.ferreiro.dragonballapp.ui.theme.Typography

@Composable
fun CharacterListItem(
    character: CharacterModel
) {
    Card(
        modifier = Modifier
            .height(500.dp)
            .width(175.dp)
            .padding(10.dp)
            .shadow(5.dp)
    ) {
        //TODO Change for AsyncImage when API is ready and delete local image
        Image(
            modifier = Modifier
                .background(
                    brush = getCharacterGradient(character.affiliation),
                )
                .fillMaxSize()
                .weight(1f)
                .padding(20.dp),
            painter = painterResource(character.image),
            contentDescription = "${character.characterName} image",
        )
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
        ) {
            //TODO Change for Text when API is ready and delete local text
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                text = character.characterName,
                style = Typography.titleMedium
            )
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = "${character.race}",
                style = Typography.titleSmall,
                color = Color(0xFFFFA000),

            )
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = "Base KI: ${character.ki}",
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = "Max. KI: ${character.maxKi}",
                fontStyle = FontStyle.Italic,
            )
            HorizontalDivider()
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                text = "${character.affiliation.toReadableString()}"
            )
        }
    }
}

@Composable
fun getCharacterGradient(affiliation: Affiliation): Brush {
    return when (affiliation) {
        Affiliation.Z_FIGHTER -> Brush.verticalGradient(colors = listOf(Color.Cyan, Color(0xFF4F68F3)))
        Affiliation.RED_RIBBON_ARMY -> Brush.verticalGradient(colors = listOf(Color.Red, Color.White))
        Affiliation.NAMEKIAN_WARRIOR -> Brush.verticalGradient(colors = listOf(Color.Green, Color(0xFF4F68F3)))
        Affiliation.FREELANCER -> Brush.verticalGradient(colors = listOf(Color.Gray, Color(0xFF4F68F3)))
        Affiliation.ARMY_OF_FRIEZA -> Brush.verticalGradient(colors = listOf(Color.Magenta, Color(0xFF4F68F3)))
        Affiliation.PRIDE_TROOPERS -> Brush.verticalGradient(colors = listOf(Color.White, Color.Red))
        Affiliation.ASSISTANT_OF_BEERUS -> Brush.verticalGradient(colors = listOf(Color.Yellow, Color(0xFF4F68F3)))
        Affiliation.ASSISTANT_OF_VERMOUD -> Brush.verticalGradient(colors = listOf(Color.Red, Color(0xFF4F68F3)))
        Affiliation.GOD -> Brush.verticalGradient(colors = listOf(Color.Yellow, Color(0xFF4F68F3)))
        Affiliation.VILLAIN -> Brush.verticalGradient(colors = listOf(Color.DarkGray, Color.Gray))
        Affiliation.OTHER -> Brush.verticalGradient(colors = listOf(Color.LightGray, Color.Black))
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterListItemPreview() {
    CharacterListItem(
        character = CharacterModel(
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
            ),
        )
    )
}