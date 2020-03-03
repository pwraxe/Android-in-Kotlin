package com.example.asynctaskdemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PDFActivity : AppCompatActivity() {

    private var onlinePDF : Button? = null
    private var offlinePDF : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        onlinePDF = findViewById(R.id.onlinePDFButton_id)
        offlinePDF = findViewById(R.id.offlinePDFButton_id)

        onlinePDF?.setOnClickListener {
            startActivity(Intent(this,OnlinePDF::class.java))
        }
        offlinePDF?.setOnClickListener {
            startActivity(Intent(this,OfflinePDF::class.java))
        }
    }
}

