package com.example.makda.sailing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.multiple_image_activity.*

class MultipleImageActivity : AbstractQuestionActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val buttonId = v!!.resources.getResourceName(v.id).split("/button")[1]

        when (buttonId.toInt() - 1) {
            questionsList?.get(questionNumber)?.answer -> rightAnswerChosen()
            else -> Toast.makeText(this, "Missed this time :(", Toast.LENGTH_SHORT).show()
        }
        loadNextQuestion()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiple_image_activity)
        setButtonsOnClickListeners()
        loadNextQuestion()
    }

    override fun setButtonImages(question: Question) {
        button1.setImageResource(IMAGES.valueOf(question.images[0]).id)
        button2.setImageResource(IMAGES.valueOf(question.images[1]).id)
        button3.setImageResource(IMAGES.valueOf(question.images[2]).id)
        button4.setImageResource(IMAGES.valueOf(question.images[3]).id)
    }

    override fun setButtonsOnClickListeners(){
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }
}
