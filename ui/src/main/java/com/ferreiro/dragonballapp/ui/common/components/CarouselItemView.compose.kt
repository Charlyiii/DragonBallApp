package com.ferreiro.dragonballapp.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.ui.theme.Typography

@Composable
fun <T> CarouselItemView(
    item: T,
    onClick: (T) -> Unit = {}
) {
    var image: String? = null
    var name: String? = null
    var ki: String? = null

    when (item) {
        is TransformationModel -> {
            val transformation = item as TransformationModel
            image = transformation.image
            name = transformation.name
            ki = transformation.ki
        }

        is CharacterModel -> {
            val character = item as CharacterModel
            image = character.image
            name = character.characterName
            ki = character.ki
        }
    }
    Card(
        modifier = Modifier
            .width(155.dp)
            .height(80.dp)
            .shadow(elevation = 5.dp)
            .clickable {
                onClick(item)
            },
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                model = image,
                contentDescription = "Item photo",
                error = painterResource(id = R.drawable.no_image),

                )
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                name?.let {
                    Text(
                        text = it,
                        style = Typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                ki?.let {
                    Row {
                        Text(
                            text = stringResource(id = R.string.ki_label),
                            style = Typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = it,
                            style = Typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarouselItemViewPreview() {
    CarouselItemView(
        item = TransformationModel(
            id = 1,
            name = "Super Saiyan",
            image = "",
            ki = "3.000.000.000"
        )
    )
}