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

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _allPlants = MutableLiveData<Response<List<Plant>>>()
    val allPlants: LiveData<Response<List<Plant>>> get() = _allPlants

    fun getAllData() {
        viewModelScope.launch {
            try {
                val response = mainRepository.getAllData()
                Log.d("MainViewModel", "getAllData response: $response")
                Log.d("MainViewModel", "getAllData response.body(): ${response.body()}")

                if (response.isSuccessful) {
                    response.body()?.let { plants ->
                        // Room에 데이터 삽입
                        mainRepository.insertPlants(plants)
                    }
                    _allPlants.postValue(response)
                } else {
                    Log.e("MainViewModel", "Error: ${response.message()}")
                    val plantsFromDb = mainRepository.getPlantsFromDatabase()
                    _allPlants.postValue(Response.success(plantsFromDb))
                }

            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching data: ${e.message}", e)
                val plantsFromDb = mainRepository.getPlantsFromDatabase()
                _allPlants.postValue(Response.success(plantsFromDb))
            }
        }
    }
}