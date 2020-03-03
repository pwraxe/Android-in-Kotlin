package com.example.axebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {

    var logo : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logo = findViewById(R.id.logoText_id)

        val rotate =  AnimationUtils.loadAnimation(this,R.anim.togather)
        logo?.startAnimation(rotate)

        val handler = Handler()
        handler.postDelayed({

            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },6000)

    }
}
