package com.sean.taipeizoo.di

import com.sean.taipeizoo.data.network.ZooApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Singleton
    @Provides
    fun provideZooApiService(): ZooApiService {
        return Retrofit.Builder()
            .baseUrl("https://data.taipei")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZooApiService::class.java)
    }
}