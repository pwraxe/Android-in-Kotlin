package com.example.materialdesignpart2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionInflater
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
        supportActionBar?.title = "Explode by XML"

        val transition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
        window?.enterTransition = transition

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun backToThePage(view: View){
        finishAfterTransition()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }

}