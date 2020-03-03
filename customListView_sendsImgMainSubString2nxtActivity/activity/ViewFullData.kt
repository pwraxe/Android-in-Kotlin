package com.example.customised_listview_cardlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ViewFullData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_full_data)

        val imageIcon = findViewById<ImageView>(R.id.row_image_id)
        val mainText = findViewById<TextView>(R.id.row_mainText_id)
        val subText = findViewById<TextView>(R.id.row_subText_id)

        val intent = intent
        imageIcon.setImageResource(intent.getIntExtra("pic",0))
        mainText.text = intent.getStringExtra("big")
        subText.text = intent.getStringExtra("small")

    }
}
