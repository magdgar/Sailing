package com.example.makda.sailing

data class QuestionsList(val version: Double, val questionsList: List<Question>)
data class Question(val question: String, val answer: Int)