package com.example.asynctaskdemo

import android.app.ProgressDialog
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.okhttp.OkHttpConnection
import com.google.okhttp.OkHttpsConnection
import java.net.URI

@Suppress("DEPRECATION")
class AudioActivity : AppCompatActivity() {

    private var audioButton : Button? = null
    private var localAudioButton : Button? = null
    lateinit var context : Context

    var audioURL = "https://talkglitz.tv/wp-content/uploads/2019/06/Shawn_Mendes_Camila_Cabello_-_Senorita_talkglitz.tv.mp3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        context = this

        audioButton = findViewById(R.id.playAudioButton_id)
        localAudioButton = findViewById(R.id.playLocalAudioButton_id)

        audioButton?.setOnClickListener {

            //Toast.makeText(this,"We just commented a code, we will update code soon to play online audio in aur app",Toast.LENGTH_LONG).show()
            LoadFromInternet().execute()

        }

        localAudioButton?.setOnClickListener {

            val mediaPlayer = MediaPlayer.create(this,R.raw.saaho_bang_bang_bgm)
            mediaPlayer.start()

        }
    }

    inner class LoadFromInternet : AsyncTask<Void,Void,Void>() {
        lateinit var progress : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setCancelable(false)
            progress.setMessage("Audio Senorita...")
            progress.setTitle("Downloading...")
            progress.show()
        }
        override fun doInBackground(vararg params: Void?): Void? {

            try {

                var mp: MediaPlayer? = MediaPlayer()
                mp?.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mp?.setDataSource(audioURL)
                mp?.prepare()
                mp?.setOnPreparedListener {
                    it.start()
                }

            }catch(ex : Exception)
            {
                Log.e("TAG","Error in Audio")
            }


            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress.dismiss()
        }

    }



}
