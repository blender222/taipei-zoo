package com.sean.taipeizoo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.taipeizoo.common.Status
import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.model.Area
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val areaRepository: AreaRepository
) : ViewModel() {
    private val _status = MutableLiveData(Status.Loading)
    val status: LiveData<Status> get() = _status

    private val _areaList = MutableLiveData<List<Area>>()
    val areaList: LiveData<List<Area>> get() = _areaList

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            if (exception is UnknownHostException) {
                _status.value = Status.NetworkError
            }
        }
        viewModelScope.launch(handler) {
            _areaList.value = areaRepository.getAll()
            _status.value = Status.Success
        }
    }
}