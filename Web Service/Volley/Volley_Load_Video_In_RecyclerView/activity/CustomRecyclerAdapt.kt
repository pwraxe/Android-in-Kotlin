package com.example.volley_recycler_load_video

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomRecyclerAdapt(
    private var context: Context,
    private var videoData: ArrayList<OnlineVideoData>
) : RecyclerView.Adapter<CustomRecyclerAdapt.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val custom_profile_pic = itemView.findViewById<ImageView>(R.id.id_custom_userImage)
        val custom_profile_name = itemView.findViewById<TextView>(R.id.id_custom_profile_name)
        val custom_view_count = itemView.findViewById<TextView>(R.id.id_custom_view_count)
        val custom_video = itemView.findViewById<VideoView>(R.id.id_custom_video)
        val custom_like_count = itemView.findViewById<TextView>(R.id.id_custom_favorite_count)
        val custom_comment_count = itemView.findViewById<TextView>(R.id.id_custom_comment)
        val custom_download_count = itemView.findViewById<TextView>(R.id.id_custom_download)
        val custom_progress = itemView.findViewById<ProgressBar>(R.id.id_custom_progressBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout, null, false))

    override fun getItemCount(): Int = videoData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(videoData[position]._userImageURL).into(holder.custom_profile_pic)

        holder.custom_profile_name?.text = "${videoData[position]._username}"

        holder.custom_view_count?.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_eye_view,
            0,
            0,
            0
        )
        holder.custom_view_count?.text = " ${videoData[position]._views}"


        holder.custom_video.setVideoURI(Uri.parse("${videoData[position]._videoURL}"))

        val mc = MediaController(context)
        holder.custom_video.setMediaController(mc)
        holder.custom_video.requestFocus()

        holder.custom_video.setOnPreparedListener {
            it.start()
        }

        holder.custom_like_count?.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_like,
            0,
            0,
            0
        )
        holder.custom_like_count?.text = " ${videoData[position]._like}"

        holder.custom_comment_count?.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_fill_comment,
            0,
            0,
            0
        )
        holder.custom_comment_count?.text = " ${videoData[position]._comment}"

        holder.custom_download_count?.setCompoundDrawablesWithIntrinsicBounds(
            R.drawable.ic_download,
            0,
            0,
            0
        )
        holder.custom_download_count?.text = " ${videoData[position]._downloads}"

    }


}