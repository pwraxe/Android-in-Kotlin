package com.example.slidetrans

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getList(view : View){
        val option = ActivityOptions.makeSceneTransitionAnimation(this)
        startActivity(Intent(this,DisplayList::class.java),option?.toBundle())
    }
}
