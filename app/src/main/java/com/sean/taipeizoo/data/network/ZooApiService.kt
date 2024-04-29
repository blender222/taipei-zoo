package com.sean.taipeizoo.data.network

import com.sean.taipeizoo.model.Animal
import com.sean.taipeizoo.model.Area
import com.sean.taipeizoo.model.JsonRoot
import retrofit2.http.GET
import retrofit2.http.Query

interface ZooApiService {
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire&limit=1000")
    suspend fun getAreaList(): JsonRoot<Area>

    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire&limit=1")
    suspend fun getArea(@Query("offset") offset: Int): JsonRoot<Area>

    @GET("api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire&limit=1000")
    suspend fun getAnimalList(@Query("q") query: String): JsonRoot<Animal>

    @GET("api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire&limit=1")
    suspend fun getAnimal(@Query("offset") offset: Int): JsonRoot<Animal>
}