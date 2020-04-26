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
        slide.slideEdge = Gravity.TOP
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
