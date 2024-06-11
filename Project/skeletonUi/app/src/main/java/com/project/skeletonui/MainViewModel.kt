package com.project.skeletonui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val mainRepository = MainRepository()

    private val _result = MutableLiveData<List<Plant>>()
    val result: LiveData<List<Plant>> = _result

    fun getAllData() {
        viewModelScope.launch {
            _result.value = mainRepository.getAllData()
        }
    }

}