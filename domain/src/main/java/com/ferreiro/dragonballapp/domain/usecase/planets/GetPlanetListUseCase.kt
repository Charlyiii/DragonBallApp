package com.ferreiro.dragonballapp.domain.usecase.planets

import com.ferreiro.dragonballapp.domain.model.PlanetModel
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.PlanetsRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetListUseCase @Inject constructor(
    private val planetsRepository: PlanetsRepository
){
    operator fun invoke(): Flow<Either<List<PlanetModel>, ErrorModel>> {
        return planetsRepository.getPlanets()
    }
}