package com.project.paging_network.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.paging_network.data.mode.GithubResponseItem
import com.project.paging_network.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val items: Flow<PagingData<GithubResponseItem>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { repository.mainPagingSource() }
    ).flow.cachedIn(viewModelScope)
}