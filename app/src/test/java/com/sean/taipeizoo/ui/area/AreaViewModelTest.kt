package com.sean.taipeizoo.ui.area

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class AreaViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var areaRepository: AreaRepository
    private lateinit var animalRepository: AnimalRepository
    private lateinit var viewModel: AreaViewModel

    @Before
    fun before() {
        savedStateHandle = mockk()
        areaRepository = mockk()
        animalRepository = mockk()
    }

    @Test
    fun init_getDataSuccess_uiDataLoaded() {
        // Arrange
        val areaId = 4
        val areaName = "沙漠動物區"
        every { savedStateHandle.get<Int>("areaId") } returns areaId
        every { savedStateHandle.get<String>("areaName") } returns areaName
        coEvery { areaRepository.getById(areaId) } returns FakeDataProvider.AreaWithId4
        coEvery { animalRepository.getList(areaName) } returns FakeDataProvider.DesertAnimalList

        // Act
        viewModel = AreaViewModel(savedStateHandle, areaRepository, animalRepository)

        // Assert
        val expected = UiData(
            area = FakeDataProvider.AreaWithId4,
            animalList = FakeDataProvider.DesertAnimalList
        )
        assertEquals(expected, viewModel.uiData.value)
        assertEquals(Status.Success, viewModel.status.value)
    }

    @Test
    fun init_getDataNetworkError_statusError() {
        // Arrange
        val areaId = 4
        val areaName = "沙漠動物區"
        every { savedStateHandle.get<Int>("areaId") } returns areaId
        every { savedStateHandle.get<String>("areaName") } returns areaName
        coEvery { areaRepository.getById(areaId) } throws UnknownHostException()
        coEvery { animalRepository.getList(areaName) } returns FakeDataProvider.DesertAnimalList

        // Act
        viewModel = AreaViewModel(savedStateHandle, areaRepository, animalRepository)

        // Assert
        assertEquals(Status.NetworkError, viewModel.status.value)
    }
}