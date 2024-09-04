package com.project.enlargeimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.enlargeimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photoView = binding.photoView
        Glide.with(this)
            .load("https://edgio.clien.net/F01/14662322/1223d7c7cb74ee.jpg?scale=width[740],options[limit]")
            .into(photoView)

    }
}