package com.example.retrofit_fetchdata_with_url

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecycler(private var context: Context,private var UserID : ArrayList<Int>,
                     private var ID : ArrayList<Int>,private var Title : ArrayList<String>,
                    private var Body : ArrayList<String>) : RecyclerView.Adapter<CustomRecycler.ViewHolder>()  {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var id  = itemView.findViewById<TextView>(R.id.id_id)
        var userId = itemView.findViewById<TextView>(R.id.id_userId)
        var title = itemView.findViewById<TextView>(R.id.id_title)
        var body = itemView.findViewById<TextView>(R.id.id_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = UserID.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id?.text = "ID : ${ID[position]}"
        holder.userId?.text = "User ID : ${UserID[position]}"
        holder.title?.text = "Title : \n ${Title[position]}"
        holder.body?.text = "Body : \n ${Body[position]}"
    }


}