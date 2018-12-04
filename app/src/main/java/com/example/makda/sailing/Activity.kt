package com.example.makda.sailing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val res = listOf("button1", "button3", "button1", "button1", "button2", "button4")
    private val questionsList = parse("/res/raw/q.json") as JsonArray<JsonObject>
    var questionNumber = 0
    var times = 0

    override fun onClick(v: View?) {
        val buttonId = v!!.resources.getResourceName(v.id).split("/button")[1]

        when (buttonId.toInt() - 1) {
            questionsList[questionNumber]["answer"] -> Toast.makeText(this, "Won!!!", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "Button clicked! $buttonId", Toast.LENGTH_SHORT).show()
        }
        times++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionNumber = Random().nextInt(questionsList.size)
        textView.text =  questionsList[questionNumber]["question"].toString()

        button1.setImageResource(R.drawable.sample_2)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button2.setImageResource(R.drawable.sample_2)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    fun parse(name: String) : Any? {
        val cls = Parser::class.java
        return cls.getResourceAsStream(name)?.let { inputStream ->
            return Parser.default().parse(inputStream)
        }
    }
}
