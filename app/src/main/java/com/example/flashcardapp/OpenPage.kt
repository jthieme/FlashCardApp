package com.example.flashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OpenPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_page)

        val buttonClick = findViewById<Button>(R.id.play_button2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Number::class.java)
            startActivity(intent)
        }
    }
}