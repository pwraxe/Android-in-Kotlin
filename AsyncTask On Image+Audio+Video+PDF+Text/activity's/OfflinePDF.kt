package com.example.asynctaskdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.github.barteksc.pdfviewer.PDFView

class OfflinePDF : AppCompatActivity() {

    private var loadOfflinePDFButton: Button? = null
    private var offlinePDFViewer: PDFView? = null

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_pdf)

        context = this
        loadOfflinePDFButton = findViewById(R.id.offlinePDFViewerButton)
        offlinePDFViewer = findViewById(R.id.offlinePDFViewer_id)

        loadOfflinePDFButton?.setOnClickListener {
            offlinePDFViewer?.fromAsset("kotlin_tutorial.pdf")?.load()

        }
    }
}
