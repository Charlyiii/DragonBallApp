package com.ferreiro.dragonballapp.domain.repository

import com.ferreiro.dragonballapp.domain.model.CharacterModel
import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    fun getPlanets(): Flow<Either<List<PlanetModel>, ErrorModel>>
    fun getPlanetById(planetId: Int): Flow<Either<PlanetModel, ErrorModel>>
}
