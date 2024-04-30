package com.sean.taipeizoo.ui.animal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class AnimalViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var animalRepository: AnimalRepository
    private lateinit var viewModel: AnimalViewModel

    @Before
    fun before() {
        savedStateHandle = mockk()
        animalRepository = mockk()
    }

    @Test
    fun init_getDataSuccess_animalLoaded() {
        // Arrange
        val animalId = 7
        every { savedStateHandle.get<Int>("animalId") } returns animalId
        coEvery { animalRepository.getById(animalId) } returns FakeDataProvider.AnimalWithId7

        // Act
        viewModel = AnimalViewModel(savedStateHandle, animalRepository)

        // Assert
        assertEquals(FakeDataProvider.AnimalWithId7, viewModel.animal.value)
        assertEquals(Status.Success, viewModel.status.value)
    }

    @Test
    fun init_getDataNetworkError_statusError() {
        // Arrange
        val animalId = 7
        every { savedStateHandle.get<Int>("animalId") } returns animalId
        coEvery { animalRepository.getById(animalId) } throws UnknownHostException()

        // Act
        viewModel = AnimalViewModel(savedStateHandle, animalRepository)

        // Assert
        assertEquals(Status.NetworkError, viewModel.status.value)
    }
}