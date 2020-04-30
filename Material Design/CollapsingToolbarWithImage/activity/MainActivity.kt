package com.example.materialdesign_part_3

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val collToolbar = findViewById<CollapsingToolbarLayout>(R.id.id_collToolbar)
        collToolbar?.title = "Collapsed Toolbar with pin Mode"

        val toolbar = findViewById<Toolbar>(R.id.id_toolbar)
        toolbar?.title = "Toolbar"
        setSupportActionBar(toolbar)

    }
}