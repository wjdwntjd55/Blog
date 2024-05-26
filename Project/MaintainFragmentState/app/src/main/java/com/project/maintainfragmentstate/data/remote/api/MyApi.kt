package com.project.maintainfragmentstate.data.remote.api

import com.project.maintainfragmentstate.data.remote.model.Plant
import retrofit2.http.GET

interface MyApi {

    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlants() : List<Plant>

}