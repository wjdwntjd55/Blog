package com.project.maintainfragmentstate.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.maintainfragmentstate.R
import com.project.maintainfragmentstate.databinding.ActivityMainBinding
import com.project.maintainfragmentstate.ui.one.OneFragment
import com.project.maintainfragmentstate.ui.two.TwoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private var oneState = true

    val TAG = "aaaaa"

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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.bottomNavigationMain.setOnApplyWindowInsetsListener(null)
        addFragment(ONE_FRAGMENT)
        bottomNavigation()
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
                TWO_FRAGMENT -> TwoFragment()
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

    fun bottomNavigation() {
        binding.bottomNavigationMain.run {
            setOnItemSelectedListener {

                when(it.itemId) {
                    R.id.item_one_menu -> {
                        if (oneState) {
                            viewModel.upOneFragment()
                        } else {
                            addFragment(ONE_FRAGMENT)
                        }

                        oneState = true
                        
                        return@setOnItemSelectedListener true
                    }

                    R.id.item_two_menu -> {
                        addFragment(TWO_FRAGMENT)
                        oneState = false
                        return@setOnItemSelectedListener true
                    }

                    else -> return@setOnItemSelectedListener false
                }

            }
        }
    }

    companion object {

        const val ONE_FRAGMENT = "OneFragment"
        const val TWO_FRAGMENT = "TwoFragment"
    }

}