package com.example.flashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is for testing purposes to make sure the activity view looks good. Delete for optimized view changing.
        val playButton = findViewById<Button>(R.id.play_button)
        playButton.setOnClickListener { setContentView(R.layout.activity_number) }
    }
}