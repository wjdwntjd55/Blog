package com.project.room_search.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.room_search.data.local.dao.SearchDao
import com.project.room_search.data.local.entities.Search

@Database(entities = [Search::class], version = 1)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao

    companion object {
        @Volatile
        private var INSTANCE: SearchDatabase? = null

        fun getDatabase(context: Context): SearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchDatabase::class.java,
                    "search_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}