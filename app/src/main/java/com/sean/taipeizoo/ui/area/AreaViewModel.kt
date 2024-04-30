package com.sean.taipeizoo.ui.area

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.data.repo.AreaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AreaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val areaRepository: AreaRepository,
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val areaId: Int = checkNotNull(savedStateHandle["areaId"])
    private val areaName: String = checkNotNull(savedStateHandle["areaName"])

    private val _status = MutableLiveData(Status.Loading)
    val status: LiveData<Status> get() = _status

    private val _uiData = MutableLiveData<UiData>()
    val uiData: LiveData<UiData> get() = _uiData

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            if (exception is UnknownHostException) {
                _status.value = Status.NetworkError
            }
        }
        viewModelScope.launch(handler) {
            val area = async {
                areaRepository.getById(areaId)
            }
            val animalList = async {
                animalRepository.getList(areaName)
            }
            _uiData.value = UiData(
                area = area.await(),
                animalList = animalList.await()
            )
            _status.value = Status.Success
        }
    }
}