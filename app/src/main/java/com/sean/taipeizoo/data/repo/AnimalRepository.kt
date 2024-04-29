package com.sean.taipeizoo.data.repo

import com.sean.taipeizoo.data.network.ZooApiService
import com.sean.taipeizoo.model.Animal
import javax.inject.Inject

interface AnimalRepository {
    suspend fun getList(areaName: String): List<Animal>

    suspend fun getById(id: Int): Animal
}

class AnimalRepositoryImpl @Inject constructor(
    private val zooApiService: ZooApiService
) : AnimalRepository {
    override suspend fun getList(areaName: String): List<Animal> {
        val location = areaName.substringBefore('（')
        return zooApiService.getAnimalList("a_location $location").result.results
    }

    override suspend fun getById(id: Int): Animal {
        return zooApiService.getAnimal(offset = id - 1).result.results.first()
    }
}