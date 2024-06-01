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
            // 초기 위치를 dataList의 크기의 중간 값으로 설정하여 사용자가 양방향으로 스크롤할 수 있도록 한다
            // (Int.MAX_VALUE / 2 % dataList.size)는 이 중간 지점을 데이터 리스트의 크기로 나눈 나머지를 구해서, 초기 위치가 데이터 리스트의 첫 번째 항목에 정확히 맞도록 조정한다
            val initialPosition = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % dataList.size)
            setCurrentItem(initialPosition, false)
        }

    }
}