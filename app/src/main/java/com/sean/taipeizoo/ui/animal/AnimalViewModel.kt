package com.sean.taipeizoo.ui.animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sean.taipeizoo.data.repo.AnimalRepository
import com.sean.taipeizoo.model.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val animalId: Int = checkNotNull(savedStateHandle["animalId"])

    private val _animal = MutableLiveData<Animal>()
    val animal: LiveData<Animal> get() = _animal

    init {
        viewModelScope.launch {
            _animal.value = animalRepository.getById(animalId)
        }
    }
}