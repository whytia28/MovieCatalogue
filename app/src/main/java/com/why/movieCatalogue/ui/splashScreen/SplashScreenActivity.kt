package com.why.movieCatalogue.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.why.movieCatalogue.R
import com.why.movieCatalogue.databinding.ActivitySplashScreenBinding
import com.why.movieCatalogue.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(activitySplashScreenBinding.root)

        supportActionBar?.hide()

        Glide.with(this)
            .load(R.drawable.background)
            .circleCrop()
            .into(activitySplashScreenBinding.imageSplash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(
                Intent(this@SplashScreenActivity, HomeActivity::class.java)
            )
            finish()
        }, DELAY)
    }
}