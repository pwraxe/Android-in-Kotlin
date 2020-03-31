package com.example.snackbar_simple

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private var top_coordinate : CoordinatorLayout? = null
    private var bottom_coordinate : CoordinatorLayout? = null
    private var simpleSnackButton : Button? = null
    private var changesInSnackButton : Button? = null
    private var snack_at_top : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        top_coordinate = findViewById(R.id.id_top_coordinate)
        bottom_coordinate = findViewById(R.id.id_bottom_coordinate)

        simpleSnackButton = findViewById(R.id.id_simpleSnack_button)
        changesInSnackButton = findViewById(R.id.id_someChangeButton)
        snack_at_top = findViewById(R.id.id_topSnackButton)

        simpleSnackButton?.setOnClickListener {

            val simpleSnack = Snackbar.make(it,"Simple Snack Bar [auto dismiss]",Snackbar.LENGTH_LONG)
            simpleSnack.show()

        }

        changesInSnackButton?.setOnClickListener {

            val advSnack = Snackbar.make(it,"We change something in snack bar",Snackbar.LENGTH_INDEFINITE)
            advSnack.setAction("OK"){view ->
                Snackbar.make(it,"Other SnackBar auto dismiss",Snackbar.LENGTH_LONG).show()
                advSnack.dismiss()
            }
            advSnack.setBackgroundTint(Color.GRAY)
            advSnack.setTextColor(Color.WHITE)
            advSnack.setActionTextColor(Color.YELLOW)

            advSnack.show()
        }

        snack_at_top?.setOnClickListener {

            val topSnack = Snackbar.make(top_coordinate as CoordinatorLayout,"SnackBar at the top",Snackbar.LENGTH_INDEFINITE)
            topSnack.setBackgroundTint(Color.YELLOW)

            topSnack.setAction("Done"){view ->
                topSnack.dismiss()
            }
            topSnack.setTextColor(Color.RED)
            topSnack.setActionTextColor(Color.BLUE)
            topSnack.show()

        }



    }
}