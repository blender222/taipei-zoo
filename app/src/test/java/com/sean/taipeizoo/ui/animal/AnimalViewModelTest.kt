package com.sean.taipeizoo.ui.animal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun init_LiveDataLoaded() {
        // Arrange
        val animalId = 7
        every { savedStateHandle.get<Int>("animalId") } returns animalId
        coEvery { animalRepository.getById(animalId) } returns FakeDataProvider.AnimalWithId7

        // Act
        viewModel = AnimalViewModel(savedStateHandle, animalRepository)

        // Assert
        Assert.assertEquals(FakeDataProvider.AnimalWithId7, viewModel.animal.value)
    }
}