package com.sean.taipeizoo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.taipeizoo.data.repo.AreaRepository
import com.sean.taipeizoo.model.Area
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val areaRepository: AreaRepository
) : ViewModel() {
    private val _areaList = MutableLiveData<List<Area>>()
    val areaList: LiveData<List<Area>> get() = _areaList

    init {
        viewModelScope.launch {
            _areaList.value = areaRepository.getAll()
        }
    }
}