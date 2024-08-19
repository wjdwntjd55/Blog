package com.project.practice_room.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.practice_room.R
import com.project.practice_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUi()
        observeData()
    }

    private fun initUi() {
        binding.run {
            adapter = MainAdapter(emptyList())
            recyclerViewMain.adapter = adapter
            recyclerViewMain.layoutManager = LinearLayoutManager(this@MainActivity)

            viewModel.getAllData()
        }
    }

    private fun observeData() {
        viewModel.allPlants.observe(this) { result ->
            if (result.isSuccessful) {
                result.body()?.let { plants ->
                    adapter.updateData(plants)
                }
            }
        }
    }
}