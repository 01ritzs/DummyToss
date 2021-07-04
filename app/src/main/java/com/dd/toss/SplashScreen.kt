package com.dd.toss

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

public class SplashScreen : AppCompatActivity() {

    private lateinit var ivSplashScreen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivSplashScreen = findViewById(R.id.ivSplashScreen)
        animationEffect()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            gotoMainScreen()
        }, 3000)
    }

    private fun animationEffect() {
        ivSplashScreen.visibility = View.VISIBLE
        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.blink_anim
        )
        ivSplashScreen.startAnimation(animation)
    }

    private fun gotoMainScreen() {
        val intent = Intent(this, MainScreen::class.java)
        startActivity(intent)
        finish()
    }
}