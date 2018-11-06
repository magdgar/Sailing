package com.example.makda.sailing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridview: GridView = findViewById(R.id.gridview)
        gridview.adapter = ImageAdapter(this)

        gridview.onItemClickListener =
                AdapterView.OnItemClickListener { parent, v, position, id ->
                    Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
                }

    }
}
