package com.example.timepass

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class DisplayData : AppCompatActivity() {

    private var captureImage : ImageView? = null
    private var userDP : CircleImageView? = null
    private var username : TextView? = null
    private var view : TextView? = null
    private var like : TextView? = null
    private var favorites : TextView? = null
    private var download : TextView? = null
    private var comment : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail"

        captureImage = findViewById(R.id._id_captureImage)
        userDP = findViewById(R.id._id_profilePIC)
        username = findViewById(R.id._id_username)

        view = findViewById(R.id._id_views)
        like = findViewById(R.id._id_likeText)
        favorites = findViewById(R.id._id_favorites)
        download = findViewById(R.id._id_download)
        comment = findViewById(R.id._id_comment)

        val intent = intent
        Glide.with(this).load(intent.getStringExtra("imageURL")).into(captureImage as ImageView)
        Glide.with(this).load(intent.getStringExtra("userDP")).into(userDP as CircleImageView)

        username?.text = intent?.getStringExtra("username")

        view?.text = "View : ${intent.getIntExtra("view",0)}"
        like?.text = "Like : ${intent.getIntExtra("like",0)}"
        favorites?.text = "Favorites : ${intent.getIntExtra("fav",0)}"
        download?.text = "Download : ${intent?.getIntExtra("download",0)}"
        comment?.text = "Comment : ${intent?.getIntExtra("comment",0)}"

    }

    /*when press default back arrow of system*/
    override fun onSupportNavigateUp(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition()
        }
        return true

    }
}
