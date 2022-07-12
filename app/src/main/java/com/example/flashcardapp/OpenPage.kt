package com.example.flashcardapp

import Database
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class OpenPage : AppCompatActivity(), DatabaseUser {

    private var db = Database()
    private lateinit var userName : EditText
    private lateinit var userResult : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_page)

        userName = findViewById<EditText>(R.id.userName)

        val buttonClick = findViewById<Button>(R.id.play_button2)


        buttonClick.setOnClickListener {
            // Check to see if the user exists in the database
            db.userExists(userName.text.toString(), this)
//            println("BUTTON CLICK - ${userResult}")

        }
    }

    override fun handleResult(result : Boolean, userResult : MutableList<String>) {
        if (result) {
            // The user already exists ... play game
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", userName.text.toString())
            intent.putExtra("userPractice", userResult[0])
            intent.putExtra("userOperator", userResult[1])
            intent.putExtra("userGenNumber", userResult[2])
            intent.putExtra("userNumsAlreadyUsed", userResult[3])
            startActivity(intent)
        } else {
            // New user ... need to ask them what number and operator they want to use
            val intent = Intent(this, StartNewGame::class.java)
            intent.putExtra("userName", userName.text.toString())
            startActivity(intent)
        }

    }
}