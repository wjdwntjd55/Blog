package com.project.room_search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.room_search.data.local.entities.Search
import com.project.room_search.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _allSearches = MutableLiveData<List<Search>>()
    val allSearches: LiveData<List<Search>> get() = _allSearches

    fun insertSearch(search: Search) {
        viewModelScope.launch {
            mainRepository.insertSearch(search)

            // 현재 검색어 목록 가져오기
            val currentSearches = _allSearches.value ?: emptyList()
            // 새 검색어 추가
            _allSearches.postValue(listOf(search) + currentSearches)
        }
    }

    fun getAllSearches() {
        viewModelScope.launch {
            val searches = mainRepository.getAllSearches()
            _allSearches.postValue(searches)
        }
    }

}