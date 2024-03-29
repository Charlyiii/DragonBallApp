package com.example.data.repository

import com.example.data.source.HomeDataSource
import com.ferreiro.dragonballapp.domain.model.HomeOptionType
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.HomeOptionsRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeOptionsRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeOptionsRepository {
    override fun getHomeOptions(): Flow<Either<List<HomeOptionType>, ErrorModel>> {
        return flow {
            when (val result = homeDataSource.getHomeOptions()) {
                is Either.Success -> {
                    emit(Either.Success(result.data))
                }

                is Either.Error -> {
                    emit(Either.Error(result.error))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}