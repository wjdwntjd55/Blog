package com.project.fragmentanimation

data class MainData(
    val imageResId: Int,
    val name: String
)

val mainDataList = listOf(
    MainData(R.drawable.img_one, "첫 번째 아이템"),
    MainData(R.drawable.img_two, "두 번째 아이템"),
    MainData(R.drawable.img_three, "세 번째 아이템"),
    MainData(R.drawable.img_four, "네 번째 아이템"),
    MainData(R.drawable.img_five, "다섯 번째 아이템"),
    MainData(R.drawable.img_six, "여섯 번째 아이템"),
    MainData(R.drawable.img_seven, "일곱 번째 아이템"),
    MainData(R.drawable.img_eight, "여덟 번째 아이템"),
    MainData(R.drawable.img_nine, "아홉 번째 아이템"),
    MainData(R.drawable.img_ten, "열 번째 아이템")
)