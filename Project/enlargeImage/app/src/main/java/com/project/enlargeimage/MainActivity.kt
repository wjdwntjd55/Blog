package com.project.enlargeimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.project.enlargeimage.databinding.ActivityMainBinding
import com.project.enlargeimage.databinding.PhotoViewOverlayBinding
import io.getstream.photoview.dialog.PhotoViewDialog

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

        val imageUrls = listOf(
            "https://photo.coolenjoy.co.kr/data/editor/1912/thumb-Bimg_1fdc4d37e9779c1ab885d0bb3bb431d7_o8lm.jpg",
            "https://photo.coolenjoy.co.kr/data/editor/1912/thumb-Bimg_1fdc4d37e9779c1ab885d0bb3bb431d7_2dc5.jpg",
            "https://photo.coolenjoy.co.kr/data/editor/1912/thumb-Bimg_1fdc4d37e9779c1ab885d0bb3bb431d7_96b2.jpg"
        )

        // 기본 PhotoViewDialog 표시
//        val button = binding.button
//        button.setOnClickListener {
//            PhotoViewDialog.Builder(context = this, images = imageUrls) { imageView, url ->
//                Glide.with(this)
//                    .load(url)
//                    .into(imageView)
//            }.build().show()
//        }

        // 커스텀 PhotoViewDialog 표시
        val button = binding.button
        button.setOnClickListener {
            val overlayBinding = PhotoViewOverlayBinding.inflate(layoutInflater)

            val photoViewDialog = PhotoViewDialog.Builder(context = this, images = imageUrls) { imageView, url ->
                Glide.with(this)
                    .load(url)
                    .into(imageView)
            }
                .withOverlayView(overlayBinding.root) // 커스텀 오버레이 뷰 설정
                .withImageChangeListener { position ->
                    // 이미지 변경 시마다 위치 텍스트 업데이트
                    val text = "${position + 1} / ${imageUrls.size}"
                    overlayBinding.photoPositionText.text = text
                }
                .build()

            photoViewDialog.show()
        }

    }
}