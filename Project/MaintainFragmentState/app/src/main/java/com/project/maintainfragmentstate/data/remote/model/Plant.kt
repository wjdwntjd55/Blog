package com.project.maintainfragmentstate.data.remote.model

data class Plant (
    val plantId : String,
    val name : String,
    val description : String,
    val growZoneNumber : Int,
    val wateringInterval : Int,
    val imageUrl : String
)
