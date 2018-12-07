package com.example.makda.sailing

import com.beust.klaxon.Json

data class QuestionsList(val version: Double, val questionsList: List<Question>)
data class Question(val question: String, val answer: Int, val images: List<Any>)