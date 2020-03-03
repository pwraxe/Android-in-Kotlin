package com.example.dialogfragmentdemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val dialog = DialogFrag()

    private var popDialogButton : Button? = null
    private var setAsFragButton : Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popDialogButton = findViewById(R.id.popDialogButton)
        setAsFragButton = findViewById(R.id.setAsFragDialogButton)


        popDialogButton?.setOnClickListener {

            dialog.show(supportFragmentManager,"dialogFrag")

        }

        setAsFragButton?.setOnClickListener {

            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.main_place,dialog,"MyDialog")
            ft.commit()
        }

    }


}