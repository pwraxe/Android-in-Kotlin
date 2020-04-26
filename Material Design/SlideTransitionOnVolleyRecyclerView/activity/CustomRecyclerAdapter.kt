package com.example.materialdesignpart2

import android.content.Context
import android.os.Build
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class CustomRecyclerAdapter (
    private var window: Window,
    private var context: Context,
    private var onlineDataList : ArrayList<OnlineData>) :RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userDP = itemView.findViewById<CircleImageView>(R.id.id_profile_pic)
        val username = itemView.findViewById<TextView>(R.id.id_username)

        val ic_views =  itemView.findViewById<ImageView>(R.id.id_view_icon)
        val ic_like = itemView.findViewById<ImageView>(R.id.id_like_icon)
        val ic_comment = itemView.findViewById<ImageView>(R.id.id_comment_icon)
        val ic_download = itemView.findViewById<ImageView>(R.id.id_download_icon)

        val count_view = itemView.findViewById<TextView>(R.id.id_view_count)
        val count_like = itemView.findViewById<TextView>(R.id.id_like_value)
        val count_comment = itemView.findViewById<TextView>(R.id.id_comment_value)
        val count_download = itemView.findViewById<TextView>(R.id.id_download_value)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.design_layout,null,false))
    }

    override fun getItemCount(): Int = onlineDataList.size

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val trans = TransitionInflater.from(context).inflateTransition(R.transition.slide)
        window.enterTransition = trans


        Glide.with(context).load(onlineDataList[position]._userDP).into(holder.userDP)
        holder.username?.text = onlineDataList[position]._username

        holder.ic_views?.setImageResource(R.drawable.ic_view)
        holder.ic_like?.setImageResource(R.drawable.ic_like)
        holder.ic_comment?.setImageResource(R.drawable.ic_comment)
        holder.ic_download?.setImageResource(R.drawable.ic_download)

        holder.count_view?.text = onlineDataList[position]._views.toString()
        holder.count_like?.text = onlineDataList[position]._likes.toString()
        holder.count_comment?.text = onlineDataList[position]._comment.toString()
        holder.count_download?.text = onlineDataList[position]._downloads.toString()


        val animation = AnimationUtils.loadAnimation(context,R.anim.slide)

        holder.userDP?.startAnimation(animation)
        holder.username?.startAnimation(animation)

        holder.ic_views?.startAnimation(animation)
        holder.ic_like?.startAnimation(animation)
        holder.ic_comment?.startAnimation(animation)
        holder.ic_download?.startAnimation(animation)

        holder.count_view?.startAnimation(animation)
        holder.count_like?.startAnimation(animation)
        holder.count_comment?.startAnimation(animation)
        holder.count_download?.startAnimation(animation)

    }

}