package com.project.skeletonui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skeletonui.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAllData()

        observeData()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun observeData() {
        viewModel.result.observe(this) { dataList ->

            // 데이터 로딩이 너무 빨리 일어나서 코루틴으로 딜레이를 줌
            GlobalScope.launch(Dispatchers.Main) {
                delay(1500)

                // 데이터 로딩 완료 시 Shimmer 애니메이션 중지
                binding.shimmerFrameLayoutMain.stopShimmer()
                binding.shimmerFrameLayoutMain.visibility = View.GONE

                binding.recyclerViewMain.run {
                    adapter = MainAdapter(dataList)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }
}