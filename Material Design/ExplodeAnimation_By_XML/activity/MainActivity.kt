package com.example.materialdesignpart2

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.core.animation.addListener
import androidx.core.view.isVisible
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun moveToNextPage(view : View){

        val option = ActivityOptions.makeSceneTransitionAnimation(this)
        val intent = Intent(this@MainActivity, NextActivity::class.java)
        startActivity(intent,option?.toBundle())
    }
}
