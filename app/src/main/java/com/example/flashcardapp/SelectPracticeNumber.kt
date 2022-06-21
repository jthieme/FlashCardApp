package com.example.flashcardapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText

class SelectPracticeNumber : AppCompatActivity() {

    private lateinit var enterPracticeNumber: EditText
    //private lateinit var submitPracticeNumber: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_practice_number)

        val submit = findViewById<Button>(R.id.submitPracticeNum)
        submit.setOnClickListener { submitPracticeNum() }

    }

    fun submitPracticeNum() : Int {
        var userPracticeNumber = enterPracticeNumber.text.toString().toInt()
        return 1

    }

}