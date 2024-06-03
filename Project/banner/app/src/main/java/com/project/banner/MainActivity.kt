package com.project.banner

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.project.banner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val handler = Handler(Looper.getMainLooper())
    private val autoScrollRunnable = Runnable {
        val currentItem = binding.viewPager2Main.currentItem
        val nextItem = currentItem + 1
        binding.viewPager2Main.setCurrentItem(nextItem, true)
    }

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

            // 초기값 설정
            binding.textViewCurrentBannerMain.text = "${initialPosition % dataList.size + 1} / ${dataList.size}"
        }

        binding.viewPager2Main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.textViewCurrentBannerMain.text = "${position % dataList.size + 1} / ${dataList.size}"
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                when (state) {

                    // 스크롤이 멈췄을 때 자동 스크롤 다시 시작
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        handler.postDelayed(autoScrollRunnable, 2000)
                    }

                    // 사용자가 드래그하기 시작했을 때 자동 스크롤 중지
                    ViewPager2.SCROLL_STATE_DRAGGING -> {
                        handler.removeCallbacks(autoScrollRunnable)
                    }

                    // 스크롤이 양쪽 끝까지 갔을 때
                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }

            }
        })

    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(autoScrollRunnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(autoScrollRunnable)
    }
}