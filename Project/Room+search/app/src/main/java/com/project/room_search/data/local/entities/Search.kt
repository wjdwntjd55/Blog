package com.project.room_search.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
data class Search(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "contents") val contents: String
)
