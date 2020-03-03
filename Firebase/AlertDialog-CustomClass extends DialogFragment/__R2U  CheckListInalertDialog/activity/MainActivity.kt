package com.example.dialogfragmentdemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var getDialog : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDialog = findViewById(R.id.getDialogButton)

        getDialog?.setOnClickListener {

            val fm = supportFragmentManager
            val dialog = DialogFrag()
            dialog.show(fm,"DialogFrag")

        }
    }
}