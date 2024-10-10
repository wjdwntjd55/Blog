package com.project.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

private const val STARTING_KEY = 0

class ArticlePagingSource : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val start = params.key ?: STARTING_KEY

        val range = start.until(start + params.loadSize)

        return LoadResult.Page(
            data = range.map { number ->
                Article(
                    id = number,
                    title = "Article $number",
                    description = "This describes article $number"
                )
            },
            prevKey = null,
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }
}