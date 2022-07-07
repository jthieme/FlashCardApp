package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlin.Number

class Number : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        // Listen for the "Let's Go!" button click to take
        // user to the next screen for practice
        val buttonClick = findViewById<Button>(R.id.button2)

        // Get the value selected for practice number
        val selectNumber = findViewById<Spinner>(R.id.numberSpinner)

        // Get which operand to use
        val selectOperand = findViewById<Spinner>(R.id.operandSpinner)

        buttonClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("selectNumber", selectNumber.selectedItem as String)
            intent.putExtra("selectOperand", selectOperand.selectedItem as String)
            startActivity(intent)
        }
    }
}