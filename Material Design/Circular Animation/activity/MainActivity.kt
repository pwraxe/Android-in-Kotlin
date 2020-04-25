package com.example.materialdesignpart2

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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

    private var smallImage: ImageView? = null
    private var bigImage: ImageView? = null
    private var relative: RelativeLayout? = null

    var centerX: Int = 0
    var centerY: Int = 0
    var radius: Float = 0.0f
    var view: View? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window?.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Circular Animation"

        smallImage = findViewById(R.id.id_small_Image)
        bigImage = findViewById(R.id.id_big_image)
        relative = findViewById(R.id.id_relative)


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun expandCollapse(view: View) {

        centerX = (view.left + view.right) / 2
        centerY = (view.top + view.bottom) / 2
        radius = max(smallImage!!.width, smallImage!!.height) * 2.0f


        if (smallImage?.visibility == View.VISIBLE) {

            smallImage?.visibility = View.GONE
            ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, radius).start()
            bigImage?.visibility = View.VISIBLE
        } else {

            val animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, radius, 0f)
            animator?.addListener(object : AnimatorListenerAdapter(){

                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    bigImage?.visibility = View.INVISIBLE
                    smallImage?.visibility = View.VISIBLE
                }

            })
            animator?.start()




        }

    }
}
