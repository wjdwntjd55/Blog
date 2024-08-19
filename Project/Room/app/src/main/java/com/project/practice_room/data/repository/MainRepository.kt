package com.project.practice_room.data.repository

import com.project.practice_room.data.local.dao.PlantDao
import com.project.practice_room.data.model.Plant
import com.project.practice_room.data.remote.api.GitApi
import com.project.practice_room.data.remote.api.RetrofitInstance

class MainRepository(private val plantDao: PlantDao) {
    private val client = RetrofitInstance.getInstance().create(GitApi::class.java)

    suspend fun getAllData() = client.getAllPlants()

    // Room에 데이터 삽입
    suspend fun insertPlants(plants: List<Plant>) {
        plantDao.insertPlants(plants)
    }

    // Room에서 데이터 가져오기
    suspend fun getPlantsFromDatabase(): List<Plant> {
        return plantDao.getAllPlants()
    }
}