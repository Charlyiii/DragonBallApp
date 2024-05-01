package com.ferreiro.dragonballapp.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.theme.Typography

@Composable
fun PlanetListItem(
    planet: PlanetModel,
    onClickItem: (PlanetModel) -> Unit = {}
) {
    Card(
        onClick = {
            onClickItem(planet)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .shadow(elevation = 20.dp, ambientColor = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            AsyncImage(
                model = planet.image,
                contentDescription = "Planet Image",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f),
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.no_image)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(all = 20.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = planet.name,
                    textAlign = TextAlign.Center,
                    style = Typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun PlanetListItemPreview() {
    PlanetListItem(
        planet = PlanetModel(
            id = 1,
            name = "Namek",
            isDestroyed = true,
            description = "Planeta natal de los Namekianos. Escenario de importantes batallas y la obtención de las Dragon Balls de Namek.",
            image = "https://firebasestorage.googleapis.com/v0/b/dragonballappapi.appspot.com/o/planets%2FNamek_U7.webp?alt=media&token=5e8edf80-b8d2-4cdd-b473-33acef0ff1f8",
        )
    )
}
