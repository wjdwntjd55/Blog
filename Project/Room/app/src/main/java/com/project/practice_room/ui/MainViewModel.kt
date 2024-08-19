package com.project.practice_room.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.practice_room.data.model.Plant
import com.project.practice_room.data.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val mainRepository = MainRepository()

    private val _allPlants = MutableLiveData<Response<List<Plant>>>()
    val allPlants: LiveData<Response<List<Plant>>> get() = _allPlants

    fun getAllData() {
        viewModelScope.launch {
            try {
                val response = mainRepository.getAllData()
                Log.d("MainViewModel", "getAllData response: $response")
                Log.d("MainViewModel", "getAllData response.body(): ${response.body()}")

                if (response.isSuccessful) {
                    _allPlants.postValue(response)
                } else {
                    Log.e("MainViewModel", "Error: ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching data: ${e.message}", e)
            }
        }
    }
}