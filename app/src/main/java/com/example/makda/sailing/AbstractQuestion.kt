package com.example.makda.sailing

import android.app.Activity
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.standard_question_activity.*
import java.io.ByteArrayOutputStream
import java.util.*

abstract class AbstractQuestionActivity: Activity() {
    protected val res = readResource("/res/raw/q.json")
    protected val questionsList = res?.let { Klaxon().parseArray<Question>(it) }
    protected var questionNumber = 0

    abstract fun setButtonImages(question: Question)
    abstract fun setButtonsOnClickListeners()

    private fun readResource(name: String) : String? {
        val cls = Parser::class.java
        val outputStream = ByteArrayOutputStream()
        return cls.getResourceAsStream(name)?.let { inputStream ->
            inputStream.use {
                input -> ByteArrayOutputStream().use {
                _ -> input.copyTo(outputStream)
            }
            }
            return String(outputStream.toByteArray(), Charsets.UTF_8)
        }
    }

    protected fun loadNextQuestion() {
        val question = getNextQuestion()
        setButtonImages(question)
        textView.text = question.question
    }

    private fun getNextQuestion() :Question {
        var nextQuestionNumber = Random().nextInt(questionsList?.size!!)
        while(questionsList.size > 1 && nextQuestionNumber == questionNumber ) {
            nextQuestionNumber = Random().nextInt(questionsList.size)
        }
        questionNumber = nextQuestionNumber
        return questionsList[questionNumber]
    }

    protected fun rightAnswerChosen() {
        Toast.makeText(this, "Good!!!", Toast.LENGTH_SHORT).show()
        counter.text = (counter.text.toString().toInt() + 1 ).toString()
    }
}