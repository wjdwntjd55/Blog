package com.project.fragmentanimation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.project.fragmentanimation.databinding.ActivityMainBinding

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
        addFragment(ONE_FRAGMENT)
    }

    fun addFragment(name: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 현재 추가된 모든 프래그먼트를 숨깁니다.
        val currentFragments = supportFragmentManager.fragments
        for (fragment in currentFragments) {
            fragmentTransaction.hide(fragment)
        }

        // 이름에 따라 새 프래그먼트를 찾거나 생성합니다.
        var newFragment = supportFragmentManager.findFragmentByTag(name)

        if (newFragment == null) {
            newFragment = when(name) {
                ONE_FRAGMENT -> OneFragment()
                else -> Fragment()
            }
            // 새 프래그먼트를 추가합니다. 태그를 사용하여 찾을 수 있도록 합니다.
            fragmentTransaction.add(R.id.fragment_container_main, newFragment, name)
        } else {
            // 프래그먼트가 이미 존재한다면 보여줍니다.
            fragmentTransaction.show(newFragment)
        }

        fragmentTransaction.commit()
    }

    companion object {
        const val ONE_FRAGMENT = "OneFragment"
    }
}