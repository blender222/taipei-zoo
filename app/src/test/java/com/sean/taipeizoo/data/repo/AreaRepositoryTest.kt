package com.sean.taipeizoo.data.repo

import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.data.network.ZooApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AreaRepositoryTest {
    private lateinit var zooApiService: ZooApiService
    private lateinit var areaRepository: AreaRepository

    @Before
    fun before() {
        zooApiService = mockk()
        areaRepository = AreaRepositoryImpl(zooApiService)
    }

    @Test
    fun getAll_execute_returnAllArea() = runTest {
        // Arrange
        coEvery { zooApiService.getAreaList() } returns FakeDataProvider.AllAreaJson

        // Act
        val actual = areaRepository.getAll()

        // Assert
        assertEquals(FakeDataProvider.AllAreaJson.result.results, actual)
    }

    @Test
    fun getById_getBy7_returnAreaWithId7() = runTest {
        // Arrange
        val id = 4
        coEvery { zooApiService.getArea(offset = id - 1) } returns FakeDataProvider.AreaWithId4Json

        // Act
        val actual = areaRepository.getById(id)

        // Assert
        assertEquals(FakeDataProvider.AreaWithId4Json.result.results.first(), actual)
    }
}