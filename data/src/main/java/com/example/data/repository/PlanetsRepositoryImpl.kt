package com.example.data.repository

import com.example.data.model.character.toCharacterModel
import com.example.data.model.planet.toPlanetModel
import com.example.data.source.PlanetDataSource
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.PlanetsRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(
    private val planetDataSource: PlanetDataSource
) : PlanetsRepository {
    override fun getPlanets(): Flow<Either<List<PlanetModel>, ErrorModel>> {
        return flow {
            when (val result = planetDataSource.getPlanetList()) {
                is Either.Success -> {
                    val planetModel =  result.data.map { planetListItemDTO ->
                        planetListItemDTO.toPlanetModel()
                    }
                    emit(Either.Success(planetModel))
                }

                is Either.Error -> {
                    val errorModel = result.error
                    emit(Either.Error(errorModel))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getPlanetById(planetId: Int): Flow<Either<PlanetModel, ErrorModel>> {
        return flow {
            when (val result = planetDataSource.getPlanetByID(planetId)) {
                is Either.Success -> {
                    val planetModel = result.data.toPlanetModel()
                    emit(Either.Success(planetModel))
                }

                is Either.Error -> {
                    val errorModel = result.error
                    emit(Either.Error(errorModel))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
