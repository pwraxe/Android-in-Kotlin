package com.example.marqueestyletextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var aboutText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aboutText = findViewById(R.id.myLine_id)
        aboutText.setSingleLine()
        aboutText.ellipsize = TextUtils.TruncateAt.MARQUEE
        aboutText.marqueeRepeatLimit = -1
        aboutText.isSelected = true
    }
}
