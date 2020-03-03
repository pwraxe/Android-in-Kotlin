package com.example.flexible_ui_interface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayData : AppCompatActivity() {

    var desc : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        desc = findViewById(R.id.descSetter_id)
        var line = intent.getStringExtra("description")
        desc?.text = line


    }
}
