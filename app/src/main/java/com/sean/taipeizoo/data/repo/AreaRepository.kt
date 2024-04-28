package com.sean.taipeizoo.data.repo

import com.sean.taipeizoo.data.network.ZooApiService
import com.sean.taipeizoo.model.Area
import javax.inject.Inject

interface AreaRepository {
    suspend fun getAll(): List<Area>

    suspend fun getById(id: Int): Area
}

class AreaRepositoryImpl @Inject constructor(
    private val zooApiService: ZooApiService
) : AreaRepository {
    override suspend fun getAll(): List<Area> {
        return zooApiService.getAreaList().result.results
    }

    override suspend fun getById(id: Int): Area {
        return zooApiService.getArea(offset = id - 1).result.results.first()
    }
}