package com.sean.taipeizoo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sean.taipeizoo.common.FakeDataProvider
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.ui.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

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
    fun init_getDataSuccess_areaListLoaded() {
        // Arrange
        coEvery { areaRepository.getAll() } returns FakeDataProvider.AllAreaList

        // Act
        viewModel = HomeViewModel(areaRepository)

        // Assert
        assertEquals(FakeDataProvider.AllAreaList, viewModel.areaList.value)
        assertEquals(Status.Success, viewModel.status.value)
    }

    @Test
    fun init_getDataNetworkError_statusError() {
        // Arrange
        coEvery { areaRepository.getAll() } throws UnknownHostException()

        // Act
        viewModel = HomeViewModel(areaRepository)

        // Assert
        assertEquals(Status.NetworkError, viewModel.status.value)
    }
}