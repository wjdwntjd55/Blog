package com.project.room_search.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.room_search.app.MyApplication
import com.project.room_search.data.repository.MainRepository
import com.project.room_search.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainAdapter = MainAdapter()

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(MainRepository((application as MyApplication).database.searchDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeData()
    }

    private fun init() {
        binding.recyclerViewMain.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        mainViewModel.getAllSearches()
    }

    private fun observeData() {

        mainViewModel.allSearches.observe(this) { searches ->
            if (searches.isEmpty()) {
                // 검색어가 없을 때의 처리
                Log.d("aaaaa", "검색 이력이 없습니다.")
            } else {
                Log.d("aaaaa", "searches: $searches")
                mainAdapter.submitList(searches)
            }
        }

    }
}