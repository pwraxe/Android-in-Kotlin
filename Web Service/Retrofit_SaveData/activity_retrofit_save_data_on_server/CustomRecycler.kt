package com.example.retrofit_save_data_on_server

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecycler(private var context: Context,
                     private var Id : ArrayList<Int>,
                     private var userID : ArrayList<Int>,
                     private var title  : ArrayList<String>,
                     private var body : ArrayList<String>) : RecyclerView.Adapter<CustomRecycler.ViewHolder>(){



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var ID = itemView.findViewById<TextView>(R.id.id_id)
        var UserId = itemView.findViewById<TextView>(R.id.id_userId)
        var Title = itemView.findViewById<TextView>(R.id.id_title)
        var Body = itemView.findViewById<TextView>(R.id.id_body)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = userID.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ID?.text = "ID : ${Id[position]}"
        holder.UserId?.text = "User Id : ${userID[position]}"
        holder.Title.text = "Title : \n ${title[position]}"
        holder.Body?.text = "Body : \n ${body[position]}"
    }

}