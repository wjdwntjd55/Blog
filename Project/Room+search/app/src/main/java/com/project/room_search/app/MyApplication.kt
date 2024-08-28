package com.project.room_search.app

import android.app.Application
import com.project.room_search.data.local.database.SearchDatabase

class MyApplication : Application() {
    val database: SearchDatabase by lazy { SearchDatabase.getDatabase(this) }
}