package com.example.data.di

import com.example.data.repository.AppRepositoryImpl
import com.ferreiro.dragonballapp.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideAppRepository(
        appActivityImpl: AppRepositoryImpl
    ) : AppRepository
}