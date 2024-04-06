package com.example.data.repository

import com.example.data.source.HomeDataSource
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.AppRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : AppRepository {

    override fun getUrlSongs(): Flow<Either<List<String>, ErrorModel>> {
        return flow {
            when (val result = homeDataSource.getSongs()) {
                is Either.Success -> {
                    val urls = result.data.map { song ->
                        song.url
                    }
                    emit(Either.Success(urls))
                }
                is Either.Error -> {
                    emit(Either.Error(result.error))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}