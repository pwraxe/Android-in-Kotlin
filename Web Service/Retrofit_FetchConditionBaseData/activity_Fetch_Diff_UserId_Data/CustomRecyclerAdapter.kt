package com.example.retrofit_getpostswithquery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerAdapter(var context: Context,
                            var UserID : ArrayList<Int>,
                            var Id : ArrayList<Int>,
                            var Title : ArrayList<String>,
                            var Body : ArrayList<String>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var userID = itemView.findViewById<TextView>(R.id.id_user_id)
        var id = itemView.findViewById<TextView>(R.id.id_id)
        var title = itemView.findViewById<TextView>(R.id.id_title)
        var body = itemView.findViewById<TextView>(R.id.id_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = UserID.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id?.text = "ID : ${Id[position]}"
        holder.userID?.text = "User ID  : ${UserID[position]}"
        holder.title?.text = "Title : \n ${Title[position]}"
        holder.body?.text = "Body : \n ${Body[position]}"
    }

}
