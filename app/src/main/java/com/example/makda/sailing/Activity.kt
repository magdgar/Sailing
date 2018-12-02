package com.example.makda.sailing

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val res = listOf("button1", "button3", "button1", "button1", "button2", "button4")
    var times = 0
    override fun onClick(v: View?) {
        val buttonId = v!!.resources.getResourceName(v.id).split("/")[1]

        when (buttonId) {
            res[times.rem(res.size)] -> Toast.makeText(this, "Won!!!", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, readFileAsTestFromRes(), Toast.LENGTH_SHORT).show()
        }
        times++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    @SuppressLint("ResourceType")
    fun readFileAsTestFromRes()
                = resources.openRawResource(R.raw.questions).readBytes().toString(Charsets.UTF_8)
}
