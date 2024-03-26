package com.ferreiro.dragonballapp.domain.repository

import com.ferreiro.dragonballapp.domain.model.SongModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getUrlSongs(): Flow<Either<List<String>, ErrorModel>>
}