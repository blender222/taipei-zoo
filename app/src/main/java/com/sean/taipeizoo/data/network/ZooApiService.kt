package com.sean.taipeizoo.data.network

import com.sean.taipeizoo.model.Area
import com.sean.taipeizoo.model.JsonRoot
import retrofit2.http.GET

interface ZooApiService {
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getAreaList(): JsonRoot<Area>
}