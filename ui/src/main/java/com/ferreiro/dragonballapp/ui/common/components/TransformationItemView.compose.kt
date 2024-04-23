package com.ferreiro.dragonballapp.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ui.R
import com.ferreiro.dragonballapp.domain.model.TransformationModel
import com.ferreiro.dragonballapp.ui.theme.Typography

//TODO refactor
@Composable
fun TransformationItemView(transformation: TransformationModel) {
    Card(
        modifier = Modifier
            .width(155.dp)
            .height(70.dp)
            .shadow(elevation = 5.dp)
            .clickable { },
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.padding(10.dp).weight(1f),
                model = transformation.image,
                contentDescription = "Transformation photo",
                error = painterResource(id = R.drawable.no_image)
            )
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = transformation.name,
                    style = Typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "KI: ${transformation.ki} ",
                    style = Typography.labelSmall,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TransformationItemViewPreview() {
    TransformationItemView(
        transformation = TransformationModel(
            id = 1,
            name = "Super Saiyan",
            image = "",
            ki = "3.000.000.000"
        )
    )
}