package com.example.dialogfragmentdemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button?.setOnClickListener {

            val fm = supportFragmentManager
            val df = DialogFrag()
            df.isCancelable = false
            df.show(fm,"Myfrag")

        }

    }
}
