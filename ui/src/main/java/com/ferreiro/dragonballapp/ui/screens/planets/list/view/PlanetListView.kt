package com.ferreiro.dragonballapp.ui.screens.planets.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.PlanetListItem

@Composable
fun PlanetListView(
    planetList: List<PlanetModel>,
    hideTopAppBar: () -> Unit = {},
    showTopAppBar: () -> Unit = {},
    onClickItem: (PlanetModel) -> Unit = {}
) {
    val scrollState = rememberLazyGridState()

    val showTopAppBarState by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }

    if(showTopAppBarState) {
        hideTopAppBar()
    } else {
        showTopAppBar()
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(planetList.filter { it.name != "Desconocido" }) { planet ->
            PlanetListItem(
                planet = planet,
                onClickItem = onClickItem
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlanetListViewPreview() {
    PlanetListView(
        planetList = listOf(planetMock, planetMock, planetMock, planetMock, planetMock, planetMock)
    )
}

private val planetMock = PlanetModel(
    id = 1,
    name = "Namek",
    isDestroyed = true,
    description = "Planeta natal de los Namekianos. Escenario de importantes batallas y la obtención de las Dragon Balls de Namek.",
    image = "https://firebasestorage.googleapis.com/v0/b/dragonballappapi.appspot.com/o/planets%2FNamek_U7.webp?alt=media&token=5e8edf80-b8d2-4cdd-b473-33acef0ff1f8",
)