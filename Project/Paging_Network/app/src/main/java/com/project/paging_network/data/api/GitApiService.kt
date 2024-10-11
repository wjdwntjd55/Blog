package com.project.paging_network.data.api

import com.project.paging_network.data.mode.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.github.com/users/google/repos?page=1&per_page=90

interface GitApiService {
    @GET("users/google/repos")
    suspend fun getData(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<GithubResponse>
}