package com.example.retrofit_fetchonlineposts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerAdapter(var context: Context, var p_id : ArrayList<Int>, var p_userId : ArrayList<Int>, var p_userTitle : ArrayList<String>, var p_userBody : ArrayList<String>)
    : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>()
{
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var post_id: TextView = itemView.findViewById(R.id.id_post_id)
        var post_userID = itemView.findViewById<TextView>(R.id.id_post_userId)
        var post_title = itemView.findViewById<TextView>(R.id.id_post_title)
        var post_body = itemView.findViewById<TextView>(R.id.id_post_body)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.cutom_layout,null,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = p_id.size

    override fun onBindViewHolder(holder: CustomRecyclerAdapter.ViewHolder, position: Int) {
        holder.post_id.text = "ID : ${p_id[position]}"
        holder.post_userID?.text = "User ID : ${p_userId[position]}"
        holder.post_title?.text = "Title : ${p_userTitle[position]}"
        holder.post_body?.text = "Body : ${p_userBody[position]}"

    }
}

