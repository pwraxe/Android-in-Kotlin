package com.example.slidetrans

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi

class DisplayList : AppCompatActivity() {

    private var listView : ListView? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_list)

        listView = findViewById(R.id.id_listView)

        val slide = Slide()
        slide.slideEdge = Gravity.TOP  // //  Gravity.LEFT | Gravity.TOP  |  Gravity.RIGHT | Gravity.BOTTOM
        slide.duration = 1000
        slide.interpolator = AnticipateOvershootInterpolator()
        window.enterTransition = slide

        listView?.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.langauge))

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onNavigateUp(): Boolean {
        finishAfterTransition()
        return true
    }
}


/*
Interpolator Class and attribute
------- Java Class------------ | ---------XML attrib---------------

AccelerateDecelerateInterpolator()	----- | ----- @android:anim/accelerate_decelerate_interpolator
AccelerateInterpolator()	        ----- | ----- @android:anim/accelerate_interpolator
AnticipateInterpolator()	        ----- | ----- @android:anim/anticipate_interpolator
AnticipateOvershootInterpolator()	----- | ----- @android:anim/anticipate_overshoot_interpolator
BounceInterpolator()	            ----- | ----- @android:anim/bounce_interpolator
CycleInterpolator()	                ----- | ----- @android:anim/cycle_interpolator
DecelerateInterpolator()	        ----- | ----- @android:anim/decelerate_interpolator
LinearInterpolator()	            ----- | ----- @android:anim/linear_interpolator
OvershootInterpolator()             ----- | ----- @android:anim/overshoot_interpolator
*
* */



