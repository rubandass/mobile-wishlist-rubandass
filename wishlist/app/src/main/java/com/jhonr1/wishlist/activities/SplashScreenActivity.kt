package com.jhonr1.wishlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.jhonr1.wishlist.R

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    /**
     * Creates animation effect when launching app
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val scaleAnimationObj = ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
            Animation.RELATIVE_TO_SELF,0.5f,
            Animation.RELATIVE_TO_SELF,0.5f)
        scaleAnimationObj.duration = 2000
        val appLogo = findViewById<ImageView>(R.id.appLogo)
        appLogo.startAnimation(scaleAnimationObj)

        handler = Handler()
        handler.postDelayed({
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
