package com.ferreiro.dragonballapp.domain.repository

import com.ferreiro.dragonballapp.domain.model.HomeOptionType
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface HomeOptionsRepository {
    fun getHomeOptions(): Flow<Either<List<HomeOptionType>, ErrorModel>>
}