package com.example.asynctaskdemo

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import java.lang.RuntimeException

class VideoActivity : AppCompatActivity() {

    var videoView : VideoView? = null
    var playVideoButton : Button? = null

    var playOfflineVideoButton : Button? = null
    var playOfflineVideoView  : VideoView? = null

    lateinit var context : Context

    lateinit var mediaController : MediaController
    lateinit var progress : ProgressDialog

    var videoURL = "rtsp://r2---sn-npoeened.googlevideo.com/Cj0LENy73wIaNAnJ2YZK0dBlvBMYDSANFC3kS8ZdMOCoAUIASARguuH8__Xald1digELbl9TYi1nTFE4dFkM/9163DDDA495214486EBDC194042F7D00A1D05495.7BF10AF4D13E162CF9A0E0ABA4BF91C3985BCAB1/yt8/1/video.3gp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        context = this
        videoView = findViewById(R.id.videoView_id)
        playVideoButton = findViewById(R.id.playVidioButton_id)
        playOfflineVideoButton = findViewById(R.id.playLocalVideoButton)
        playOfflineVideoView = findViewById(R.id.offlineVideo_id)

        playOfflineVideoButton?.setOnClickListener {

            var uri = Uri.parse("android.resource://$packageName/${R.raw.jaguar_xj}")
            playOfflineVideoView?.setVideoURI(uri)
            playOfflineVideoView?.start()
        }


        playVideoButton?.setOnClickListener {
            LoadOnlineVideo().execute()
        }

    }

    inner class LoadOnlineVideo : AsyncTask<Void,Void,Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setTitle("Downloading...")
            progress.setMessage("Fetching Video")
            progress.show()
        }
        override fun doInBackground(vararg params: Void?): Void? {
            playOnlineVideo()

            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress.dismiss()

        }
    }
    fun playOnlineVideo() {
        try {
                videoView?.setVideoPath(videoURL)
                videoView?.start()

        } catch (re: RuntimeException) {
            Log.e("TAG_A", "$re")
        }
    }

}
