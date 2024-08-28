package com.project.room_search.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.room_search.data.local.entities.Search
import java.util.concurrent.Flow

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: Search)

    @Delete
    suspend fun deleteSearch(search: Search)

    @Query("SELECT * FROM search")
    suspend fun getAllSearches(): List<Search>

    @Query("DELETE FROM search")
    suspend fun deleteAllSearches()
}