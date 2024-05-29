package com.project.maintainfragmentstate.ui.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.maintainfragmentstate.data.remote.model.Plant
import com.project.maintainfragmentstate.data.remote.repository.Repository
import kotlinx.coroutines.launch

class OneViewModel: ViewModel() {

    private val repository = Repository()

    private val _result = MutableLiveData<List<Plant>>()
    val result: LiveData<List<Plant>> = _result

    fun getAllData() {
        viewModelScope.launch {
            _result.value = repository.getAllData()
        }
    }

}