package com.example.makda.sailing

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast

class SingleImageActivity: Activity(), View.OnClickListener {
    override fun onClick(view: View?) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_a ->
                    if (checked) {
                        Toast.makeText(this, "Radio A", Toast.LENGTH_SHORT).show()
                    }
                else -> Toast.makeText(this, "Not radio A", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_image_activity)
    }
}