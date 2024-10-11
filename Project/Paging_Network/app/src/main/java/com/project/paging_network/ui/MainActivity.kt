package com.project.paging_network.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.paging_network.R
import com.project.paging_network.data.repository.MainRepository
import com.project.paging_network.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository = MainRepository()

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainAdapter = MainAdapter()

        binding.recyclerViewMain.apply {
            adapter = mainAdapter.withLoadStateFooter(
                footer = MyLoadStateAdapter()
            )
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // SwipeRefreshLayout 설정
        binding.swipeRefreshLayoutMain.setOnRefreshListener {
            mainAdapter.refresh() // 데이터 새로 고침
            binding.swipeRefreshLayoutMain.isRefreshing = false
        }

        lifecycleScope.launch {
            viewModel.items.collectLatest {
                mainAdapter.submitData(it)
            }
        }

        // 로딩 상태를 감지하여 SwipeRefreshLayout의 상태를 업데이트
        lifecycleScope.launch {
            mainAdapter.loadStateFlow.collect { loadStates ->
                // 로딩 중이면 새로 고침을 표시
                binding.swipeRefreshLayoutMain.isRefreshing = loadStates.refresh is LoadState.Loading
            }
        }
    }
}