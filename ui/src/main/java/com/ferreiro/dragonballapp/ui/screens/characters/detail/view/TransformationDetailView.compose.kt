package com.ferreiro.dragonballapp.ui.screens.characters.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.ui.theme.Typography

//TODO se podría usar la misma pantalla para mostrar el detalle de un personaje
@Composable
fun TransformationDetailView(
    transformation: TransformationModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        //.background(getCharacterGradient(character.affiliation))
    ) {
        TransformationDetailHeader(
            transformation = transformation,
            modifier = Modifier.weight(5f)
        )
        TransformationDetailFooter(
            transformation = transformation,
            modifier = Modifier.weight(0.8f)
        )
    }
}

//TODO refactorizar
@Composable
fun TransformationDetailHeader(
    transformation: TransformationModel,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()

    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = transformation.image,
            contentDescription = "${transformation.name} image",
            error = painterResource(id = R.drawable.goku_preview)
        )
    }
}

@Composable
fun TransformationDetailFooter(
    transformation: TransformationModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Box {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                {
                    //TODO Refactorizar texto en un componente
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            //TODO: localizar strings
                            text = "KI: ",
                            textAlign = TextAlign.Start,
                            style = Typography.titleMedium
                        )
                        Text(
                            //TODO: localizar strings
                            text = transformation.ki,
                            textAlign = TextAlign.Start,
                            style = Typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TransformationDetailViewPreview() {
    TransformationDetailView(
        transformation = TransformationModel(
            id = 0,
            name = "SSJ",
            image = "https://dragonball-api.com/characters/goku_normal.webp", //TODO: Cambiar a URL de imagen,
            ki = "60.000.000"
        )
    )
}
