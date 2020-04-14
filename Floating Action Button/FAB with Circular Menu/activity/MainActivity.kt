package com.example.snackdemo2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

    private var mainFAB : FloatingActionButton? = null
    private var fab1 : FloatingActionButton? = null
    private var fab2 : FloatingActionButton? = null
    private var fab3 : FloatingActionButton? = null

    private var isMenuOpen = false;

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "FAB with Circular menu"

        mainFAB = findViewById(R.id.id_mainFAB)
        fab1 = findViewById(R.id.id_fab1)
        fab2 = findViewById(R.id.id_fab2)
        fab3 = findViewById(R.id.id_fab3)

        fab1?.translationY = 200f
        fab2?.translationZ = 500f
        fab3?.translationX = 200f

        fab1?.visibility = View.GONE
        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE

        mainFAB?.setOnClickListener {
            if(isMenuOpen) openMenu() else closeMenu()

            fab1?.setOnClickListener {
                Toast.makeText(this,"Fab 1 click",Toast.LENGTH_SHORT).show()
                closeMenu()
            }
            fab2?.setOnClickListener {
                Toast.makeText(this,"Fab 2 click",Toast.LENGTH_SHORT).show()
                closeMenu()
            }
            fab3?.setOnClickListener {
                Toast.makeText(this,"Fab 3 click",Toast.LENGTH_SHORT).show()
                closeMenu()
            }

        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun openMenu() {

        isMenuOpen = !isMenuOpen

        mainFAB?.animate()?.rotation(45f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab1?.animate()?.translationY(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab2?.animate()?.translationZ(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab3?.animate()?.translationX(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab1?.visibility = View.VISIBLE
        fab2?.visibility = View.VISIBLE
        fab3?.visibility = View.VISIBLE


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun closeMenu() {

        isMenuOpen = !isMenuOpen

        mainFAB?.animate()?.rotation(0f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab1?.animate()?.translationY(300f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab2?.animate()?.translationZ(300f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()
        fab3?.animate()?.translationX(300f)?.setInterpolator(OvershootInterpolator())?.setDuration(300)?.start()

        fab1?.visibility = View.GONE
        fab2?.visibility = View.GONE
        fab3?.visibility = View.GONE

    }



}