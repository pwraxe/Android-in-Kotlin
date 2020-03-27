package com.example.retrofit_fetch_photo

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomRecycler(
    private var context: Context,
    private var onlinePhoto: ArrayList<OnlinePhotos>
) : RecyclerView.Adapter<CustomRecycler.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val custom_id = itemView.findViewById<TextView>(R.id.id_custom_id)
        val custom_album = itemView.findViewById<TextView>(R.id.id_custom_album_id)
        val custom_url_1 = itemView.findViewById<ImageView>(R.id.id_custom_pic1)
        val custom_url_2 = itemView.findViewById<ImageView>(R.id.id_custom_pic2)
        val custom_title = itemView.findViewById<TextView>(R.id.id_custom_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout, null, false))

    override fun getItemCount(): Int = onlinePhoto.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.custom_id?.text = "ID : ${onlinePhoto[position]._id}"
        holder.custom_album?.text = "Album ID : ${onlinePhoto[position]._album_id}"

        //Glide.with(context).load("${onlinePhoto[position]._url_1}").into(holder.custom_url_1)
        Picasso.get().load(Uri.parse("${onlinePhoto[position]._url_1}")).into(holder.custom_url_1)
        //holder.custom_url_1?.setImageURI(Uri.parse("${onlinePhoto[position]._url_1}"))


        //Glide.with(context).load(onlinePhoto[position]._url_2).into(holder.custom_url_2)
        Picasso.get().load(Uri.parse("${onlinePhoto[position]._url_2}")).into(holder.custom_url_2)

        holder.custom_title?.text = "Title : \n ${onlinePhoto[position]._title}"
    }
}