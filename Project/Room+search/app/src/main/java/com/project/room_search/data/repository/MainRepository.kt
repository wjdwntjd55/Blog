package com.project.room_search.data.repository

import com.project.room_search.data.local.dao.SearchDao
import com.project.room_search.data.local.entities.Search

class MainRepository(private val searchDao: SearchDao) {

    suspend fun insertSearch(search: Search) {
        searchDao.insertSearch(search)
    }

    suspend fun deleteSearch(search: Search) {
        searchDao.deleteSearch(search)
    }

    suspend fun getAllSearches(): List<Search> {
        return searchDao.getAllSearches()
    }

    suspend fun deleteAllSearches() {
        searchDao.deleteAllSearches()
    }
}