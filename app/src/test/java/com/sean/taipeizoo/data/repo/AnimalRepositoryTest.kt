package com.sean.taipeizoo.data.repo

import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.data.network.ZooApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AnimalRepositoryTest {
    private lateinit var zooApiService: ZooApiService
    private lateinit var animalRepository: AnimalRepository

    @Before
    fun before() {
        zooApiService = mockk()
        animalRepository = AnimalRepositoryImpl(zooApiService)
    }

    @Test
    fun getList_getByAreaName_returnCorrectList() = runTest {
        // Arrange
        val areaName = "沙漠動物區"
        coEvery { zooApiService.getAnimalList("a_location $areaName") } returns FakeDataProvider.DesertAnimalJson

        // Act
        val actual = animalRepository.getList(areaName)

        // Assert
        Assert.assertEquals(FakeDataProvider.DesertAnimalJson.result.results, actual)
    }

    @Test
    fun getById_getBy7_returnAnimalWithId7() = runTest {
        // Arrange
        val id = 7
        coEvery { zooApiService.getAnimal(offset = id - 1) } returns FakeDataProvider.AnimalWithId7Json

        // Act
        val actual = animalRepository.getById(id)

        // Assert
        Assert.assertEquals(FakeDataProvider.AnimalWithId7Json.result.results.first(), actual)
    }
}