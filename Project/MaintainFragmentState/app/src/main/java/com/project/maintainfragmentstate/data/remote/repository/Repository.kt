package com.project.maintainfragmentstate.data.remote.repository

import com.project.maintainfragmentstate.data.remote.api.MyApi
import com.project.maintainfragmentstate.data.remote.api.RetrofitInstance

class Repository {

    private val client = RetrofitInstance.getInstance().create(MyApi::class.java)

    suspend fun getAllData() = client.getAllPlants()
}