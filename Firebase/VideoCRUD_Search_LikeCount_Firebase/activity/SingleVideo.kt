package com.example.mxplayerdemo

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.example.mxplayerdemo.databinding.ActivitySingleVideoBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.my_own_controller.view.*

private const val VIDEO_URI = "video_url"

class SingleVideo : AppCompatActivity() {

    private lateinit var binding : ActivitySingleVideoBinding

    private lateinit var videoUri : Uri

    private lateinit var simpleExoPlayer: SimpleExoPlayer

    private var isInFullScreen = false

    private lateinit var fullScreenIcon : ImageView

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_single_video)

        binding.idProgress.visibility = View.VISIBLE

        videoUri = intent.getStringExtra(VIDEO_URI).toUri()

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        simpleExoPlayer.prepare(buildMediaSource(videoUri))
        binding.idPlayerView.player = simpleExoPlayer
        binding.idPlayerView.controllerAutoShow = true
        binding.idPlayerView.controllerShowTimeoutMs = 10000
        binding.idPlayerView.useController = true
        binding.idPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
        binding.idPlayerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH
        binding.idPlayerView.showController()
        simpleExoPlayer.playWhenReady

        binding.idProgress.visibility = View.GONE

        fullScreenIcon = binding.idPlayerView.exoplayer_fullscreen_icon


        fullScreenIcon.setOnClickListener {

            if(isInFullScreen){

                fullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_fullscreen_enter))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                isInFullScreen = false
            }else{
                fullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_fullscreen_exit))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                isInFullScreen = true

            }
        }
    }

    override fun onBackPressed() {
        if(isInFullScreen){
            fullScreenIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_fullscreen_enter))
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onBackPressed()
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        return ProgressiveMediaSource.Factory(DefaultDataSourceFactory(this, "Exo_Player_Demo")).createMediaSource(uri)
    }
}