package com.example.asynctaskdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var imageButton : Button? = null
    private var audioButton : Button? = null
    private var videoButton : Button? = null
    private var pdfButton : Button? = null
    private var textButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton = findViewById(R.id.imageActivityButton)
        audioButton = findViewById(R.id.audioActivityButton)
        videoButton = findViewById(R.id.videoActivityButton)
        pdfButton = findViewById(R.id.pdfActivityButton)
        textButton = findViewById(R.id.textActivityButton)

        imageButton?.setOnClickListener {
            startActivity(Intent(this,ImageActivity::class.java))
        }
        audioButton?.setOnClickListener {
            startActivity(Intent(this,AudioActivity::class.java))
        }
        videoButton?.setOnClickListener {
            startActivity(Intent(this,VideoActivity::class.java))
        }
        pdfButton?.setOnClickListener {
            startActivity(Intent(this,PDFActivity::class.java))
        }
        textButton?.setOnClickListener {
            startActivity(Intent(this,TextActivity::class.java))
        }
    }
}
