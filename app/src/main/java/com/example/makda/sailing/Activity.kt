package com.example.makda.sailing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val res = readResource("/res/raw/q.json")
    private val questionsList = res?.let { Klaxon().parseArray<Question>(it) }
    private var questionNumber = 0

    override fun onClick(v: View?) {
        val buttonId = v!!.resources.getResourceName(v.id).split("/button")[1]

        when (buttonId.toInt() - 1) {
            questionsList?.get(questionNumber)?.answer -> Toast.makeText(this, "Won!!!", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Missed this time :( $buttonId", Toast.LENGTH_SHORT).show()
        }
        loadNextQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadNextQuestion()
    }

    private fun loadNextQuestion() {
        questionNumber = Random().nextInt(questionsList?.size!!)
        val question = questionsList[questionNumber]
        setButtonImages(question)
        setButtonsOnClickListeners()
        textView.text = question.question
    }

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

    private fun setButtonImages(question: Question) {
        button1.setImageResource(IMAGES.valueOf(question.images[0]).id)
        button2.setImageResource(IMAGES.valueOf(question.images[1]).id)
        button3.setImageResource(IMAGES.valueOf(question.images[2]).id)
        button4.setImageResource(IMAGES.valueOf(question.images[3]).id)
    }

    private fun setButtonsOnClickListeners(){
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }
}
