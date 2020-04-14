package com.example.snackdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

    private var fab1 : FloatingActionButton? = null
    private var fab2 : FloatingActionButton? = null
    private var fab3 : FloatingActionButton? = null

    private var isMenuOpen = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "FAB with X menu"

        fab1 = findViewById(R.id.id_fab1)
        fab2 = findViewById(R.id.id_fab2)
        fab3 = findViewById(R.id.id_fab3)


        fab2?.translationX = -200f
        fab3?.translationX = -200f

        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE

        fab1?.setOnClickListener {
            if(isMenuOpen) openMenu() else closeMenu()

            fab2?.setOnClickListener {
                Toast.makeText(this,"Fab 2 clicked",Toast.LENGTH_SHORT).show()
                closeMenu()
            }
            fab3?.setOnClickListener {view->
                val bar = Snackbar.make(view,"3rd FAB Clicked",Snackbar.LENGTH_INDEFINITE)
                bar.setAction("Close"){
                    bar.dismiss()
                    closeMenu()
                }
                bar.show()
            }

        }
    }

    private fun openMenu(){

        isMenuOpen = !isMenuOpen
        fab1?.animate()?.rotation(45f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab2?.animate()?.translationX(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab3?.animate()?.translationX(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab2?.visibility = View.VISIBLE
        fab3?.visibility = View.VISIBLE

    }

    private fun closeMenu(){

        isMenuOpen = !isMenuOpen
        fab1?.animate()?.rotation(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab2?.animate()?.translationX(200f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab3?.animate()?.translationX(300f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE
    }
}