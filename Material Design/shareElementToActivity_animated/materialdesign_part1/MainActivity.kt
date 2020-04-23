package com.example.materialdesign_part1

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    private var radioICon : ImageView? = null
    private var androidText : TextView? = null
    private var shareIcon : ImageView? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioICon = findViewById(R.id.id_radioIcon)
        androidText = findViewById(R.id.id_androidText)
        shareIcon = findViewById(R.id.id_shareIcon)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun migrateData(view: View) {

        val pair = arrayOf(
            Pair(radioICon as View,"radioIconName"),
            Pair(androidText as View,"androidText"),
            Pair(shareIcon as View,"shareIconName"))

        val actOption = ActivityOptions.makeSceneTransitionAnimation(this, *pair)
        startActivity(Intent(this,DisplayData::class.java),actOption?.toBundle())

    }
}