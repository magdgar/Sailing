package com.example.makda.sailing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.StringReader
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val res = listOf("button1", "button3", "button1", "button1", "button2", "button4")
    val reso = readResource("/res/raw/q.json")
    private val questionsList = reso?.let { Klaxon().parseArray<Question>(it) }
    var questionNumber = 0
    var times = 0

    override fun onClick(v: View?) {
        val buttonId = v!!.resources.getResourceName(v.id).split("/button")[1]

        when (buttonId.toInt() - 1) {
            questionsList?.get(questionNumber)?.answer -> Toast.makeText(this, "Won!!!", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Button clicked! $buttonId", Toast.LENGTH_SHORT).show()
        }
        times++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionNumber = Random().nextInt(questionsList?.size!!)
        textView.text = questionsList?.get(questionNumber).question
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button2.setImageResource(R.drawable.sample_2)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    fun readResource(name: String) : String? {
        val cls = Parser::class.java
        val outputStream = ByteArrayOutputStream()
        return cls.getResourceAsStream(name)?.let { inputStream ->
            inputStream.use {
                input -> ByteArrayOutputStream().use {
                output -> input.copyTo(outputStream)
            }
            }
            return String(outputStream.toByteArray(), Charsets.UTF_8)
        }
    }
}
