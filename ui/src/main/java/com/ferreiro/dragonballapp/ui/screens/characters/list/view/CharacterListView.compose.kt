package com.ferreiro.dragonballapp.ui.screens.characters.list.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ferreiro.dragonballapp.domain.model.Affiliation
import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.ui.common.components.CharacterListItem
import com.ferreiro.dragonballapp.ui.common.extensions.toReadableString
import com.ferreiro.dragonballapp.ui.screens.characters.list.utils.GroupingType
import com.ferreiro.dragonballapp.ui.theme.dbz_font
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

//TODO Refactor
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterListView(
    characterList: List<CharacterModel>,
    //hideTopAppBar: () -> Unit = {},
    //showTopAppBar: () -> Unit = {},
    groupingType: GroupingType = GroupingType.NONE,
    onClickItem: (CharacterModel) -> Unit = {}
) {
    val scrollState = rememberLazyListState()
    val groupedCharacterList = groupCharacters(characterList, groupingType)

    /*TODO mejorar, hacer que al dejar de hacer scroll se muestre
    if(showTopAppBarState) {
        hideTopAppBar()
    } else {
        showTopAppBar()
    }
    */
    LaunchedEffect(groupingType) {
        Log.d("CharacterListView", "Grouping Type Changed: $groupingType")
        delay(500)
        Log.d("CharacterListView", "Scroll to top")
        scrollState.animateScrollToItem(0)
    }

    LazyColumn(
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        groupedCharacterList.forEach { (headText, gridItems) ->
            if (groupingType != GroupingType.NONE) {
                stickyHeader {
                    val formattedHeader = when (groupingType) {
                        GroupingType.AFFILIATION -> (headText as Affiliation).toReadableString(LocalContext.current)
                        else -> headText.toString()
                    }
                    Text(
                        text = formattedHeader,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = dbz_font,
                    )
                }
            }

            items(gridItems.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { gridItem ->
                        CharacterGridItem(gridItem, onClickItem = onClickItem)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterGridItem(character: CharacterModel, onClickItem: (CharacterModel) -> Unit = {}) {
    // Implementa aquí cómo mostrar un elemento individual en la cuadrícula
    CharacterListItem(
        character = character,
        onClickItem = onClickItem
    )
}

private fun groupCharacters(
    characterList: List<CharacterModel>,
    groupingType: GroupingType
): Map<Any, List<CharacterModel>> {
    return when (groupingType) {
        GroupingType.AZ -> characterList.groupBy { it.characterName.first().toString() }
        GroupingType.ZA -> characterList.groupBy { it.characterName.first().toString() }
        GroupingType.AFFILIATION -> characterList.groupBy { it.affiliation }
        GroupingType.RACE -> characterList.groupBy { it.race }
        GroupingType.GENDER -> characterList.groupBy { it.gender }
        else -> mapOf("All" to characterList)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CharacterListViewPreview() {
    CharacterListView(
        characterList = listOf(
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock,
            characterMock
        )
    )
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
    description = "The strongest warrior in the universe",
    originPlanet = PlanetModel(
        id = 0,
        name = "Earth",
        description = "The planet where Goku was born",
        isDestroyed = false
    )
)
