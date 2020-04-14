package com.example.snackdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){

    private var fab1 : FloatingActionButton? = null
    private var fab2 : FloatingActionButton? = null
    private var fab3 : FloatingActionButton? = null

    private var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fab1 = findViewById(R.id.id_fab1)
        fab2 = findViewById(R.id.id_fab2)
        fab3 = findViewById(R.id.id_fab3)

//        fab2?.alpha = 0f
//        fab3?.alpha = 0f

        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE

        fab2?.translationY = 500f
        fab3?.translationY = 500f

        closeMenu()

        fab1?.setOnClickListener {
            if (isOpen) openMenu() else closeMenu()
            fab2?.setOnClickListener {
                Toast.makeText(this,"Click on Fab 2",Toast.LENGTH_SHORT).show()
                closeMenu()
            }

            fab3?.setOnClickListener {
                Toast.makeText(this,"Click on Fab 3",Toast.LENGTH_SHORT).show()
                closeMenu()
            }
        }
    }

    private fun openMenu(){
        isOpen = !isOpen
        fab1?.animate()?.setInterpolator(OvershootInterpolator())?.rotation(45f)?.setDuration(300)?.start()
        fab2?.animate()?.translationY(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(500)?.start()
        fab3?.animate()?.translationY(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(500)?.start()
        fab2?.visibility = View.VISIBLE
        fab3?.visibility = View.VISIBLE
    }

    private fun closeMenu() {
        isOpen = !isOpen
        fab1?.animate()?.setInterpolator(OvershootInterpolator())?.rotation(0f)?.setDuration(300)?.start()

        fab2?.animate()?.translationY(200f)?.setInterpolator(OvershootInterpolator())?.setDuration(500)?.start()    // small jump
        fab3?.animate()?.translationY(500f)?.setInterpolator(OvershootInterpolator())?.setDuration(500)?.start()    // long jump

        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE

    }

}