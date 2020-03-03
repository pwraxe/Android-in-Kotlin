package com.example.asynctaskdemo

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.lang.NullPointerException
import java.net.URL

class ImageActivity : AppCompatActivity() {

    private var loadImageButton : Button? = null
    private var loadedImage : ImageView? = null

    private var loadLocalImageButton : Button? = null
    private var loadedLocalImage : ImageView? = null


    private var customLinkInputBox : EditText? = null
    private var customLinkButton : Button? = null
    private var customLinkImageView : ImageView? = null


    var imageURL : String = ""

    lateinit var bit : Bitmap



    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_image)
        context = this

            loadImageButton = findViewById(R.id.loadImageButton)
            loadedImage = findViewById(R.id.loadedImage_id)

        loadLocalImageButton = findViewById(R.id.loadLocalImageButton)
        loadedLocalImage = findViewById(R.id.loadedLocalImage_id)

        customLinkInputBox = findViewById(R.id.customLink_id)
        customLinkButton = findViewById(R.id.loadCustomLinkImageButton)
        customLinkImageView = findViewById(R.id.loadedCustomImage_id)

        loadImageButton?.setOnClickListener {
            LoadImage().execute()
        }

        loadLocalImageButton?.setOnClickListener {
            loadedLocalImage?.setImageResource(R.drawable.lines)
        }

        customLinkButton?.setOnClickListener {
            imageURL = customLinkInputBox?.text.toString()
            LoadCustomLinkImage().execute(imageURL)
        }



    }

    inner class LoadImage : AsyncTask<Void,Void,Void>() {

        lateinit var progress : ProgressDialog
        lateinit var bit : Bitmap
        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setMessage("Image Downloading...")
            progress.setCancelable(false)
            progress.show()
        }

        override fun doInBackground(vararg params: Void?): Void? {

            var url = URL("https://electrek.co/wp-content/uploads/sites/3/2018/01/2018-jaguar-xjr575-e1516672804195.jpg?quality=82&strip=all")
            var conn = url.openConnection()
            var ins = conn.getInputStream()
            bit = BitmapFactory.decodeStream(ins)
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            loadedImage?.setImageBitmap(bit)
            progress.dismiss()
        }
    }
    //----------------------------------------------------------------------------------------------
    inner class LoadCustomLinkImage : AsyncTask<String,Void,Void>() {
        lateinit var progress : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setMessage("Fetching Image From Internet...")
            progress.setCancelable(false)
            progress.show()
        }
        override fun doInBackground(vararg params: String?): Void? {

            var link = params[0].toString()
            Log.e("LINK","$link")
            var url = URL(link)
            val conn = url.openConnection()
            val inputStream = conn.getInputStream()
            bit = BitmapFactory.decodeStream(inputStream)

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            customLinkImageView?.setImageBitmap(bit)
            progress.dismiss()
        }
    }
}
