package com.example.autocompletetextviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private var text1 : TextView? = null
    private var text2 : TextView? = null
    private var text3 : TextView? = null
    private var text4 : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.text1_id)
        text2 = findViewById(R.id.text2_id)
        text3 = findViewById(R.id.text3_id)
        text4 = findViewById(R.id.text4_id)

        text1?.setSingleLine()
        text1?.isSelected = true

        text2?.setSingleLine()
        text2?.isSelected = true

        text3?.setSingleLine()
        text3?.isSelected = true

        text4?.setSingleLine()
        text4?.isSelected = true





    }
}
