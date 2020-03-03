package com.example.asynctaskdemo

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.github.barteksc.pdfviewer.PDFView
import java.io.FileNotFoundException
import java.net.URL

class OnlinePDF : AppCompatActivity() {

    private var loadOnlinePDFButton : Button? = null
    private var onlinePDFViewer : PDFView? = null
    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_pdf)
        context = this

        loadOnlinePDFButton = findViewById(R.id.onlinePDFViewerButton)
        onlinePDFViewer = findViewById(R.id.onlinePDFViewer_id)

        loadOnlinePDFButton?.setOnClickListener {
            LoadOnlinePDF().execute()
        }
    }

    inner class LoadOnlinePDF : AsyncTask<Void, Void, Void>() {

        lateinit var progress : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setTitle("Downloading")
            progress.setMessage("Fetching PDF From Internet")
            progress.setCancelable(false)
            progress.show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            getPDF()
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress.dismiss()
        }

    }
    fun getPDF() {
        try {
            val url = URL("https://kotlinlang.org/docs/kotlin-docs.pdf")
            val conn = url.openConnection()
            val inputStream = conn.getInputStream()
            onlinePDFViewer?.fromStream(inputStream)?.load()
        }
        catch (ex : FileNotFoundException)
        {
            Log.e("PDF","$ex")
        }

    }

}
