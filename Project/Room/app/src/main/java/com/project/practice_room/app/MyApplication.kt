package com.project.practice_room.app

import android.app.Application
import com.project.practice_room.data.local.database.PlantRoomDatabase

class MyApplication : Application() {

    // Room 데이터베이스 초기화
    val database: PlantRoomDatabase by lazy { PlantRoomDatabase.getDatabase(this) }
}