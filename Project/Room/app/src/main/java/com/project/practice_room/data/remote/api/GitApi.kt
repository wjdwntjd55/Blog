package com.project.practice_room.data.remote.api

import com.project.practice_room.data.model.Plant
import retrofit2.Response
import retrofit2.http.GET

interface GitApi {
    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlants() : Response<List<Plant>>
}