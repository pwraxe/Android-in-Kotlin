package com.example.timepass

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.internal.ContextUtils.getActivity
import de.hdodenhof.circleimageview.CircleImageView

class CustomAdapter (var context: Context,
                     private var postArray : ArrayList<OnlineData>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>()

{
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val container = itemView.findViewById<CardView>(R.id.id_container)
        val userDP = itemView.findViewById<CircleImageView>(R.id.id_circularImage)
        val username = itemView.findViewById<TextView>(R.id.id_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.raw_design,null,false))

    override fun getItemCount(): Int = postArray.size

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(postArray[position]._userDP).into(holder.userDP)
        holder.username?.text = postArray[position]._username

        holder.container?.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.userDP?.transitionName = "userProfilePic"
                holder.username?.transitionName = "userName"

                val data = arrayOf(Pair(holder.userDP as View,"userProfilePic"),Pair(holder.username as View, "userName"))
                val actOption = ActivityOptions.makeSceneTransitionAnimation(getActivity(context) ,*data)
                val intent = Intent(context,DisplayData::class.java)
                intent.putExtra("imageURL",postArray[position]._image_url)
                intent.putExtra("userDP",postArray[position]._userDP)
                intent.putExtra("username",postArray[position]._username)
                intent.putExtra("view",postArray[position]._views)
                intent.putExtra("like",postArray[position]._likes)
                intent.putExtra("fav",postArray[position]._favorites)
                intent.putExtra("download",postArray[position]._downloads)
                intent.putExtra("comment",postArray[position]._comments)

                ContextCompat.startActivity(context, intent,actOption.toBundle())
            }


        }
    }

}