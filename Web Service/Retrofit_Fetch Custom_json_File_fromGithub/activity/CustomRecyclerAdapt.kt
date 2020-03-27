package com.example.retrofit_fetch_custom_data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapt (private var context: Context, private var onlineData : ArrayList<MyOnlineData>)
    : RecyclerView.Adapter<CustomRecyclerAdapt.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val custom_name = itemView.findViewById<TextView>(R.id.id_custom_name)
        val custom_age = itemView.findViewById<TextView>(R.id.id_custom_age)
        val custom_email = itemView.findViewById<TextView>(R.id.id_custom_email)
        val custom_mobile = itemView.findViewById<TextView>(R.id.id_custom_mobile)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = onlineData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.custom_name?.text = "Name : \n${onlineData[position]._name}"
        holder.custom_age?.text = "${onlineData[position]._age} Year"
        holder.custom_email?.text = "Email : \n${onlineData[position]._email}"
        holder.custom_mobile?.text = "Mobile No : ${onlineData[position]._mobile}"
    }

}