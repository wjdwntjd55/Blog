package com.project.paging.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class ArticleViewModel(
    repository: ArticleRepository
) : ViewModel() {

    val items: Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 50),
        pagingSourceFactory = {
            repository.articlePagingSource()
        }
    )
        .flow
        .cachedIn(viewModelScope)
}