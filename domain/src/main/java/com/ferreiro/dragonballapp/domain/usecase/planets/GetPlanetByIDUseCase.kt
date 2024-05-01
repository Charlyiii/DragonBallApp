package com.ferreiro.dragonballapp.domain.usecase.planets

import com.ferreiro.dragonballapp.domain.repository.PlanetsRepository
import javax.inject.Inject

class GetPlanetByIDUseCase @Inject constructor(
    private val planetsRepository: PlanetsRepository
){
    operator fun invoke(id: Int) = planetsRepository.getPlanetById(id)
}
