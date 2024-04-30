package com.sean.taipeizoo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var areaRepository: AreaRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun before() {
        areaRepository = mockk()
    }

    @Test
    fun init_LiveDataLoaded() {
        // Arrange
        coEvery { areaRepository.getAll() } returns FakeDataProvider.AllAreaList

        // Act
        viewModel = HomeViewModel(areaRepository)

        // Assert
        assertEquals(FakeDataProvider.AllAreaList, viewModel.areaList.value)
    }
}