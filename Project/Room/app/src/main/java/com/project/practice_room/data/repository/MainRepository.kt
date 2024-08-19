package com.project.practice_room.data.repository

import com.project.practice_room.data.remote.api.GitApi
import com.project.practice_room.data.remote.api.RetrofitInstance

class MainRepository {
    private val client = RetrofitInstance.getInstance().create(GitApi::class.java)

    suspend fun getAllData() = client.getAllPlants()
}