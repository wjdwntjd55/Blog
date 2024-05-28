package com.project.maintainfragmentstate.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    private val _oneState = MutableLiveData<Boolean>()
    val oneState: LiveData<Boolean> = _oneState

    fun upOneFragment() {
        _oneState.value = true
    }

}