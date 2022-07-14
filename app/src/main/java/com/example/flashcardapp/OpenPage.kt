package com.example.flashcardapp

import Database
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**************************************
* OPEN PAGE
* This class will have the text input
* which will be used to see if the user
* exists or not. If they do exist, then
* get their last saved data. Otherwise,
* create their user document in the database
***************************************/
class OpenPage : AppCompatActivity(), DatabaseUser {

    // Initialize member variables (attributes)
    private var db = Database()
    private lateinit var userName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_page)

        // Get the id from the screen for the text input
        userName = findViewById<EditText>(R.id.userName)

        // Get the id from the screen for the button
        val buttonClick = findViewById<Button>(R.id.play_button2)

        // When the button is clicked
        buttonClick.setOnClickListener {
            // Check to see if the user exists in the database
            db.userExists(userName.text.toString(), this)
        }
    }

    /**************************************
    * HANDLE RESULT
    * This is a callback function that the
    * Interface uses. This will prevent
    * the app from moving forward in
    * operation before it should
    * (while checking to see if a user is
    * in the database or not)
    ***************************************/
    override fun handleResult(result : Boolean, userResult : MutableList<String>) {
        // The user already exists
        if (result) {
            // Send the user to the practice page
            val intent = Intent(this, MainActivity::class.java)

            // And send their last saved data with them
            intent.putExtra("userName", userName.text.toString())
            intent.putExtra("userPractice", userResult[0])
            intent.putExtra("userOperator", userResult[1])
            intent.putExtra("userGenNumber", userResult[2])
            intent.putExtra("userNumsAlreadyUsed", userResult[3])

            // Go to the practice page
            startActivity(intent)
        // Otherwise they are a new user
        } else {
            // Send them to the number and operator selection screen
            val intent = Intent(this, StartNewGame::class.java)

            // Send their entered username as well
            intent.putExtra("userName", userName.text.toString())

            // Go to the selection screen
            startActivity(intent)
        }
    }
}