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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Animate FAB"

        fab1 = findViewById(R.id.id_fab1)
        fab1?.setOnClickListener{view ->
            fab1?.animate()?.setInterpolator(OvershootInterpolator())?.rotation(45f)?.setDuration(300)?.start()
            val bar = Snackbar.make(view,"No Database Found...",Snackbar.LENGTH_INDEFINITE)
            bar.setAction("Close"){
                bar.dismiss()
                fab1?.animate()?.setInterpolator(OvershootInterpolator())?.rotation(0f)?.setDuration(300)?.start()
            }
            bar.show()
        }
    }
}