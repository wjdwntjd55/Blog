package com.project.banner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.banner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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

        val dataList = arrayListOf<DataPage>().apply {
            add(DataPage(android.R.color.holo_red_light, "1 Page"))
            add(DataPage(android.R.color.holo_orange_dark, "2 Page"))
            add(DataPage(android.R.color.holo_green_dark, "3 Page"))
            add(DataPage(android.R.color.holo_blue_light, "4 Page"))
            add(DataPage(android.R.color.holo_blue_bright, "5 Page"))
            add(DataPage(android.R.color.black, "6 Page"))
        }

        binding.viewPager2Main.run {
            adapter = ViewPager2Adapter(dataList)
        }

    }
}