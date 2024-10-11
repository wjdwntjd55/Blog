package com.project.paging.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.paging.data.ArticleRepository
import com.project.paging.data.ArticleViewModel
import com.project.paging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val articleViewModel: ArticleViewModel by viewModels {
        ArticleViewModelFactory(ArticleRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleAdapter = ArticleAdapter()

        binding.list.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            articleViewModel.items.collectLatest {
                articleAdapter.submitData(it)
            }
        }

    }
}

//  1. lifecycleScope.launch { ... }
//  - lifecycleScope는 현재 액티비티의 생명 주기를 기반으로 하는 Coroutine 스코프입니다. 이를 사용하면 UI 생명 주기와 연결된 Coroutine을 실행할 수 있습니다.
//  - launch는 새로운 Coroutine을 시작하는 함수로, 비동기 작업을 수행합니다. 이 블록 내의 코드는 Coroutine으로 실행되며, UI 스레드와는 별도로 실행됩니다.

//  2. articleViewModel.items.collectLatest { ... }
//  - articleViewModel.items는 Flow<PagingData<Article>> 타입으로, Paging 라이브러리를 통해 페이지 단위로 로드된 기사 데이터를 포함합니다.
//  - collectLatest는 Flow에서 값을 수집하는 함수입니다. 이 함수는 새로운 데이터가 수집될 때마다 이전 수집 작업을 취소하고 최신 데이터만 처리합니다.
//  - 즉, 데이터가 빠르게 변경되는 경우, 이전 데이터의 처리를 무시하고 항상 최신 데이터만을 UI에 반영합니다.

//  3. articleAdapter.submitData(it)
//  - collectLatest 블록 내에서 it은 현재 수집된 PagingData<Article>를 나타냅니다.
//  - articleAdapter.submitData(it)를 호출하여 새로운 데이터를 어댑터에 제출합니다. 이 메서드는 어댑터에 데이터의 변경 사항을 알려주고, RecyclerView를 업데이트하여 새로운 기사를 표시합니다.
