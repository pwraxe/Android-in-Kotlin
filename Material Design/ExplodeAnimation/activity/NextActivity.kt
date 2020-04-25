package com.example.materialdesignpart2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import android.view.animation.Interpolator
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.finishAfterTransition

class NextActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Explode"

        val explode = Explode()
        explode.duration = 700
        window.enterTransition = explode

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }

}