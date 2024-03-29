package com.ferreiro.dragonballapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.ferreiro.dragonballapp.ui.common.components.HomeOptionItem
import com.ferreiro.dragonballapp.ui.vo.HomeOptionVO

@Composable
fun HomeView(
    homeOptionList: List<HomeOptionVO>,
    onClickItem: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        HomeViewHeader()
        HomeViewBody(homeOptionList, onClickItem)
    }
}

@Composable
fun HomeViewHeader() {
    Box(modifier = Modifier.padding(bottom = 30.dp)) {
        Image(
            painter = painterResource(id = R.drawable.db_title),
            contentDescription = "Dragon Ball App",
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
        )
    }
}

@Composable
fun HomeViewBody(
    homeOptionList: List<HomeOptionVO>,
    onClickItem: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(10.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(homeOptionList) { homeOptionItem ->
            HomeOptionItem(
                homeOptionVO = homeOptionItem,
                onClick = {
                    onClickItem( homeOptionItem.nameResource)
                })
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeViewPreview() {
    HomeView(
        listOf(
            HomeOptionVO(R.string.characters_text, R.drawable.characters_img),
            HomeOptionVO(R.string.planets_text, R.drawable.planets_img)
        )
    ){}
}