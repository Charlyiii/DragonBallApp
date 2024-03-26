package com.ferreiro.dragonballapp.domain.usecase.app

import com.ferreiro.dragonballapp.domain.model.error.ErrorModel
import com.ferreiro.dragonballapp.domain.repository.AppRepository
import com.ferreiro.dragonballapp.domain.utils.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUrlSongsUseCase @Inject constructor(
    private val appRepository: AppRepository
){
    operator fun invoke(): Flow<Either<List<String>, ErrorModel>>{
        return appRepository.getUrlSongs()
    }
}