package com.sean.taipeizoo.di

import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.data.repo.AreaRepositoryImpl
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.data.repo.AnimalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAreaRepository(impl: AreaRepositoryImpl): AreaRepository

    @Singleton
    @Binds
    abstract fun bindAnimalRepository(impl: AnimalRepositoryImpl): AnimalRepository
}