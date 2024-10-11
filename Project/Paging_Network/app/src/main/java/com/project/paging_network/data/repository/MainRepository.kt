package com.project.paging_network.data.repository

import com.project.paging_network.data.api.GitApiService
import com.project.paging_network.data.api.RetrofitInstance
import com.project.paging_network.data.paging.MainPagingSource

class MainRepository {
    private val gitApiService = RetrofitInstance.getInstance().create(GitApiService::class.java)

    fun mainPagingSource() = MainPagingSource(gitApiService)
}