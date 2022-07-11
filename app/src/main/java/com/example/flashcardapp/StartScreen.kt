package com.example.flashcardapp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardapp.OpenPage
import com.example.flashcardapp.R

@Suppress("DEPRECATION")
class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        Handler().postDelayed({
            val intent = Intent(this, OpenPage::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
