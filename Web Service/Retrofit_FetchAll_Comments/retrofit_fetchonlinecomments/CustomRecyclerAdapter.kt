package com.example.retrofit_fetchonlinecomments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerAdapter (var context: Context, var com_id : ArrayList<Int>, var com_post_id : ArrayList<Int>,
                             var com_name : ArrayList<String>, var com_email : ArrayList<String>,
                             var com_body : ArrayList<String>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val comment_id = itemView.findViewById<TextView>(R.id.id_comm_id)
        val comment_post_id = itemView.findViewById<TextView>(R.id.id_comm_post)
        val comment_name = itemView.findViewById<TextView>(R.id.id_comm_name)
        val comment_email = itemView.findViewById<TextView>(R.id.id_comm_email)
        val comment_body = itemView.findViewById<TextView>(R.id.id_comm_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerAdapter.ViewHolder = ViewHolder(itemView = LayoutInflater.from(context).inflate(R.layout.custom_view,null,false))

    override fun getItemCount(): Int = com_id.size

    override fun onBindViewHolder(holder: CustomRecyclerAdapter.ViewHolder, position: Int) {

        holder.comment_id?.text = "ID : ${com_id[position]}"
        holder.comment_post_id?.text = "Post ID : ${com_post_id[position]}"
        holder.comment_name?.text = "Name : ${com_name[position]}"
        holder.comment_email?.text = "Email ID : ${com_email[position]}"
        holder.comment_body?.text = "Comment : ${com_body[position]}"
    }
}