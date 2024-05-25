package com.project.maintainfragmentstate.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.project.maintainfragmentstate.R
import com.project.maintainfragmentstate.databinding.ActivityMainBinding
import com.project.maintainfragmentstate.ui.one.OneFragment

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

        binding.bottomNavigationMain.setOnApplyWindowInsetsListener(null)
        replaceFragment(ONE_FRAGMENT)
    }

    fun replaceFragment(name: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val newFragment: Fragment = when(name) {
            ONE_FRAGMENT -> OneFragment()

            else -> Fragment()
        }

        fragmentTransaction.replace(R.id.fragment_container_main, newFragment)
        fragmentTransaction.commit()
    }

    companion object {

        const val ONE_FRAGMENT = "OneFragment"
    }

}