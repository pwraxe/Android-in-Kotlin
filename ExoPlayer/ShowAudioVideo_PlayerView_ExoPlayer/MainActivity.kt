package com.example.exo_player_demo

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.exo_player_demo.databinding.ActivityMainBinding
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
/**
 * Add following line to manifest in <activity ==>MainActivity
 *       android:configChanges="keyboard|keyboardHidden|orientation|screenSize|screenLayout|smallestScreenSize|uiMode">
 *
 *
 *
 *ExoPlayer Dependencies
 *
 * // exoplayer
 * implementation 'com.google.android.exoplayer:exoplayer:2.7.3'
 * implementation 'com.google.android.exoplayer:exoplayer-core:2.10.5'
 * implementation 'com.google.android.exoplayer:exoplayer-dash:2.10.5'
 * implementation 'com.google.android.exoplayer:exoplayer-ui:2.10.5'
 * implementation 'com.google.android.exoplayer:exoplayer-hls:2.7.3'
 * implementation 'com.google.android.exoplayer:exoplayer-smoothstreaming:2.7.3'
 *
 * */




class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var simpleExoPlayer: SimpleExoPlayer

    private var playWhenReady = true
    private var currentWindow = 0
    private var playBackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }


    private fun initializedPlayer() {

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        binding.idVideoView.player = simpleExoPlayer

        // https://mediarobotvideo.s3.amazonaws.com/fp_video_new.mp4   // Video url
        // https://storage.googleapis.com/exoplayer-test-media-0/play.mp3  // Audio Url

        simpleExoPlayer.playWhenReady = playWhenReady
        simpleExoPlayer.seekTo(currentWindow,playBackPosition)
        simpleExoPlayer.prepare(buildMediaSource(Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")))


        //var uri1 = Uri.parse("storage.googleapis.com/exoplayer-test-media-0/play.mp3")  // audio
    }


    private fun buildMediaSource(uri: Uri) : MediaSource = ProgressiveMediaSource
            .Factory(DefaultDataSourceFactory(this,"Exo_Player_Demo"))
            .createMediaSource(uri)


    override fun onStart() {
        super.onStart()

        if(Util.SDK_INT >= 24)
            initializedPlayer()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if(Util.SDK_INT < 24)
            initializedPlayer()
    }

    override fun onPause() {
        super.onPause()
        if(Util.SDK_INT < 24)
            releasePlayer()
    }

    override fun onStop() {
        super.onStop()

        if(Util.SDK_INT >= 24)
            releasePlayer()
    }


    private fun releasePlayer(){
        playWhenReady = simpleExoPlayer.playWhenReady
        playBackPosition = simpleExoPlayer.currentPosition
        currentWindow = simpleExoPlayer.currentWindowIndex
        simpleExoPlayer.release()

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hideSystemUi(){
        binding.idVideoView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            View.SYSTEM_UI_FLAG_FULLSCREEN
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    }
}


















