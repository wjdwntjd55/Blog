package com.project.practice_room.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.practice_room.data.model.Plant

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlants(plants: List<Plant>)

    @Query("SELECT * FROM plants")
    suspend fun getAllPlants(): List<Plant>
}