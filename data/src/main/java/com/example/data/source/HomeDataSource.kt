package com.example.data.source

import com.example.data.model.common.SongList
import com.ferreiro.dragonballapp.domain.model.HomeOptionType
import com.ferreiro.dragonballapp.domain.model.SongModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import javax.inject.Inject

class HomeDataSource @Inject constructor(){

    fun getSongs(): Either<List<SongModel>, ErrorModel>{
        return Either.Success(SongList.songs)
    }

    fun getHomeOptions(): Either<List<HomeOptionType>, ErrorModel> {
        val optionList = listOf(
            HomeOptionType.CHARACTERS, HomeOptionType.PLANETS
        )
        return Either.Success(optionList)
    }
}