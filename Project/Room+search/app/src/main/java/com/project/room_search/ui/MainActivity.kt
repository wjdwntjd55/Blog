package com.project.room_search.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.room_search.app.MyApplication
import com.project.room_search.data.local.entities.Search
import com.project.room_search.data.repository.MainRepository
import com.project.room_search.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MainAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val mainAdapter = MainAdapter(this)

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(MainRepository((application as MyApplication).database.searchDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeData()
        inputSearch()
        deleteAllSearches()
    }

    private fun init() {
        binding.recyclerViewMain.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            // 애니메이션 제거
            itemAnimator = null
        }
        mainViewModel.getAllSearches()
    }

    private fun observeData() {

        mainViewModel.allSearches.observe(this) { searches ->
            if (searches.isEmpty()) {
                // 검색어가 없을 때의 처리
                Log.d("aaaaa", "검색 이력이 없습니다.")
                mainAdapter.submitList(emptyList())
                binding.textViewDeleteAll.visibility = View.GONE
                binding.textViewEmpty.visibility = View.VISIBLE
                binding.recyclerViewMain.visibility = View.GONE
            } else {
                Log.d("aaaaa", "searches: $searches")
                mainAdapter.submitList(searches) {
                    // 새로 추가된 데이터를 기준으로 스크롤
                    binding.recyclerViewMain.scrollToPosition(0)
                }
                binding.textViewDeleteAll.visibility = View.VISIBLE
                binding.textViewEmpty.visibility = View.GONE
                binding.recyclerViewMain.visibility = View.VISIBLE
            }
        }

    }

    private fun inputSearch() {
        // EditText에서 엔터 키 이벤트 처리
        binding.editTextMain.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = binding.editTextMain.text.toString().trim()
                if (searchText.isNotEmpty()) {
                    // 입력한 검색어를 ViewModel에 추가
                    mainViewModel.insertSearch(Search(contents = searchText)) // Search 객체 생성
                    binding.editTextMain.text?.clear() // 입력 필드 초기화
                }
                true
            } else {
                false
            }
        }
    }

    private fun deleteAllSearches() {
        binding.textViewDeleteAll.setOnClickListener {
            mainViewModel.deleteAllSearches()
        }
    }

    override fun removeItemClick(item: Search) {
        mainViewModel.deleteSearch(item)
    }
}