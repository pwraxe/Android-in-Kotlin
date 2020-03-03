package com.example.gridviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ViewFullData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_full_data)

        val imageSet = findViewById<ImageView>(R.id.row_image_id)
        val mainSet = findViewById<TextView>(R.id.row_main_id)

        imageSet.setImageResource(intent.getIntExtra("pic",0))
        mainSet.text = intent.getStringExtra("mainText")

    }
}
