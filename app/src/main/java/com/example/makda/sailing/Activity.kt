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
import java.io.StringReader
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val res = listOf("button1", "button3", "button1", "button1", "button2", "button4")
    private val questionsList = Klaxon().parseArray<Question>("[\n" +
            "  {\n" +
            "    \"question\": \"There is the questions?\",\n" +
            "    \"answer\": 1,\n" +
            "    \"images\": [\"R.drawable.sample_2\", \"R.drawable.sample_3\", \"R.drawable.sample_4\", \"R.drawable.sample_5\"]\n" +
            "  },\n" +
            "  {\n" +
            "    \"question\": \"Whats your name?\",\n" +
            "    \"answer\": 2,\n" +
            "    \"images\": []\n" +
            "  },\n" +
            "  {\n" +
            "    \"question\": \"Yet another question?\",\n" +
            "    \"answer\": 1,\n" +
            "    \"images\": []\n" +
            "  }\n" +
            "]")
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
        textView.text = questionsList?.get(questionNumber)?.question ?: button1.setImageResource(R.drawable.sample_2 ).toString()
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

    fun streamingArray(json: String) {
        val klaxon = Klaxon()
        JsonReader(StringReader(json)).use { reader ->
            val result = arrayListOf<Question>()
            reader.beginArray {
                while (reader.hasNext()) {
                    val person = klaxon.parse<Question>(reader)
                    if (person != null) {
                        result.add(person)
                    }
                }
            }
        }
    }
}
