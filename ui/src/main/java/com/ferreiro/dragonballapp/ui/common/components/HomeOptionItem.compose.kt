package com.ferreiro.dragonballapp.ui.common.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.theme.Typography
import com.ferreiro.dragonballapp.ui.vo.HomeOptionVO

@Composable
fun HomeOptionItem(
    homeOptionVO: HomeOptionVO,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .height(150.dp)
            .width(300.dp)
            .padding(8.dp)
            .background(Color.White)
            .shadow(elevation = 3.dp, shape = MaterialTheme.shapes.medium)
            .clickable { onClick() }

    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                painter = painterResource(id = homeOptionVO.imgResource),
                contentDescription = "HomeOption image",
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = homeOptionVO.nameResource),
                style = Typography.titleLarge,
            )
        }
    }
}

@Preview
@Composable
fun HomeOptionItemPreview() {
    HomeOptionItem(
        homeOptionVO = HomeOptionVO(
            nameResource = R.string.characters_text,
            imgResource = R.drawable.characters_img,
            destinationResource = R.id.action_homeFragment_to_characterListFragment
        ),
        onClick = {}
    )
}

