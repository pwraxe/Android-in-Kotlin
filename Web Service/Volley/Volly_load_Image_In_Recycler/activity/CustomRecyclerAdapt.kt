package com.example.volley_recycler_load_img

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.net.HttpURLConnection
import java.net.URL

class CustomRecyclerAdapt (var context: Context, var SinglePosts : ArrayList<OnlineData>)
    : RecyclerView.Adapter<CustomRecyclerAdapt.ViewHolder>()  {


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var custom_profile_pic = itemView.findViewById<CircleImageView>(R.id.id_custom_userImage)
        var custom_profile_name = itemView.findViewById<TextView>(R.id.id_custom_profile_name)
        var custom_uploaded_image = itemView.findViewById<ImageView>(R.id.id_custom_image)
        var custom_views = itemView.findViewById<TextView>(R.id.id_custom_view_count)
        var custom_favorite_count = itemView.findViewById<TextView>(R.id.id_custom_favorite_count)
        var custom_like_count = itemView.findViewById<TextView>(R.id.id_custom_like)
        var custom_download_count = itemView.findViewById<TextView>(R.id.id_custom_download)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = SinglePosts.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(SinglePosts[position]._user_Image_url).into(holder.custom_profile_pic)
        holder.custom_profile_name?.text = "${SinglePosts[position]._user_name}"
        holder.custom_views?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_eye_view,0,0,0)
        holder.custom_views?.text = " ${SinglePosts[position]._views}"
        holder.custom_uploaded_image?.setImageURI(Uri.parse(SinglePosts[position]._image_url))


        Glide.with(context).load(SinglePosts[position]._image_url).into(holder.custom_uploaded_image)

        holder.custom_favorite_count?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite,0,0,0)
        holder.custom_favorite_count?.text = " ${SinglePosts[position]._fav}"

        holder.custom_like_count?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fill_comment,0,0,0)
        holder.custom_like_count?.text = " ${SinglePosts[position]._likes}"

        holder.custom_download_count?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_download,0,0,0)
        holder.custom_download_count?.text = " ${SinglePosts[position]._downloads}"


    }




}
