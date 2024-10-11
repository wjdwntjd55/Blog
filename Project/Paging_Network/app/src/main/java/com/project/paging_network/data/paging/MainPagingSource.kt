package com.project.paging_network.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.paging_network.data.api.GitApiService
import com.project.paging_network.data.mode.GithubResponseItem

private const val STARTING_KEY = 1

class MainPagingSource(private val gitApiService: GitApiService) : PagingSource<Int, GithubResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponseItem> {
        val page = params.key ?: STARTING_KEY

        return try {
            // API 호출
            val response = gitApiService.getData(page, params.loadSize)

            // 응답 처리
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!

                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = page + (params.loadSize / 30)
                )
            } else {
                LoadResult.Error(Exception("Failed to load data"))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }


    }

    override fun getRefreshKey(state: PagingState<Int, GithubResponseItem>): Int? {
        TODO("Not yet implemented")
    }
}