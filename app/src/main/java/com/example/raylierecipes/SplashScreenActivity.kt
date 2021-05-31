package com.example.raylierecipes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 2500)

    }
}