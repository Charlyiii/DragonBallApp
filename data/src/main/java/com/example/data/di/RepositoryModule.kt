package com.example.data.di

import com.example.data.repository.AppRepositoryImpl
import com.example.data.repository.CharactersRepositoryImpl
import com.example.data.repository.HomeOptionsRepositoryImpl
import com.example.data.repository.PlanetsRepositoryImpl
import com.ferreiro.dragonballapp.domain.repository.AppRepository
import com.ferreiro.dragonballapp.domain.repository.CharactersRepository
import com.ferreiro.dragonballapp.domain.repository.HomeOptionsRepository
import com.ferreiro.dragonballapp.domain.repository.PlanetsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideAppRepository(
        appActivityImpl: AppRepositoryImpl
    ) : AppRepository

    @Binds
    fun provideHomeOptionsRepository(
        homeOptionsRepositoryImpl: HomeOptionsRepositoryImpl
    ) : HomeOptionsRepository

    @Binds
    fun provideCharactersRepository(
        charactersRepositoryImpl : CharactersRepositoryImpl
    ) : CharactersRepository

    @Binds
    fun providePlanetsRepository(
        planetsRepositoryImpl : PlanetsRepositoryImpl
    ) : PlanetsRepository
}
