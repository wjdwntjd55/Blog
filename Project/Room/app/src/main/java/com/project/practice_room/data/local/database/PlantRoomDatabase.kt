package com.project.practice_room.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.practice_room.data.local.dao.PlantDao
import com.project.practice_room.data.model.Plant

@Database(entities = [Plant::class], version = 1)
abstract class PlantRoomDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantRoomDatabase? = null // 싱글톤 인스턴스

        fun getDatabase(context: Context): PlantRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // INSTANCE가 null일 경우에만 데이터베이스를 생성
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantRoomDatabase::class.java,
                    "plant_database"    // 데이터베이스 이름
                )
                    .fallbackToDestructiveMigration()   // 마이그레이션이 없을 경우 기존 데이터 삭제
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}