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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_page)

        userName = findViewById<EditText>(R.id.userName)

        val buttonClick = findViewById<Button>(R.id.play_button2)


        buttonClick.setOnClickListener {
            println("OPEN PAGE - USER NAME IS: ${userName.text}")
            if (db.userExists(userName.text.toString(), this)) {
                handleResult()
            } else {
                val intent = Intent(this, StartNewGame::class.java)
                intent.putExtra("userName", userName.text.toString())
                startActivity(intent)
            }



        }
    }

    override fun handleResult() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userName", userName.text.toString())
        startActivity(intent)

    }
}