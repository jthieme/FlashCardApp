package com.example.flashcardapp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.flashcardapp.OpenPage
import com.example.flashcardapp.R

/**************************************
* START (SPLASH) SCREEN
* This class will serve as the Splash
* Screen for the app
***************************************/
@Suppress("DEPRECATION")
class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Set the whole window to full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )

        // Keep this screen alive as the app "loads" for 3 seconds
        Handler().postDelayed({
            // When the 3 seconds are up
            val intent = Intent(this, OpenPage::class.java)

            // Go to the OpenPage screen
            startActivity(intent)

            // End this screen
            finish()
        }, 3000)
    }
}
