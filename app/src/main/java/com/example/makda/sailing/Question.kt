package com.example.makda.sailing

data class Question(val question: String, val answer: Int, val images: List<String>)
data class StandardQuestion(val question: String, val answer: Int, val options: List<String>)

enum class IMAGES (val id: Int){
    IMG_TWO(R.drawable.sample_2),
    IMG_THREE(R.drawable.sample_3),
    IMG_FOUR(R.drawable.sample_4),
    IMG_FIVE(R.drawable.sample_5),
    BERTH_BAN(R.drawable.zakaz_cumowania),
    ANCHOR_BAN(R.drawable.zakaz_kotwiczenia),
    PASS_BAN(R.drawable.zakaz_mijania),
    PRONATION_BAN(R.drawable.zakaz_nawracania)
}