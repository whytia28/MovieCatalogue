package com.example.movieCatalogue.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.movieCatalogue.databinding.ActivitySplashScreenBinding
import com.example.movieCatalogue.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val DELAY = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(activitySplashScreenBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this@SplashScreenActivity, HomeActivity::class.java)
            )
            finish()
        }, DELAY.toLong())
    }
}