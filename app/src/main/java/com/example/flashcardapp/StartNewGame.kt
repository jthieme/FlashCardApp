package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

/**************************************
* START NEW GAME
* This class will be used by first time
* users and for everyone after they have
* finished all 12 numbers to practice,
* or to change what to practice at any time
***************************************/
class StartNewGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        // Initialize the member variable
        var userName = intent.getStringExtra("userName")

        // Listen for the "Let's Go!" button click to take
        // user to the next screen for practice
        val buttonClick = findViewById<Button>(R.id.button2)

        // Get the value selected for practice number
        val selectNumber = findViewById<Spinner>(R.id.numberSpinner)

        // Get which operand to use
        val selectOperand = findViewById<Spinner>(R.id.operandSpinner)

        // When the "Let's Go!" button is clicked
        buttonClick.setOnClickListener {
            // Set the next page as MainActivity to practice selected items
            val intent = Intent(this, MainActivity::class.java)

            // Send the items to the next page
            intent.putExtra("userName", userName)
            intent.putExtra("selectNumber", selectNumber.selectedItem as String)
            intent.putExtra("selectOperand", selectOperand.selectedItem as String)

            // Start the next page
            startActivity(intent)
        }
    }
}