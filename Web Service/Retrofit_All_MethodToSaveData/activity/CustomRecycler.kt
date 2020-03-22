package com.example.retrofit_savedata_method3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecycler (
    private var context : Context,
    private var _id : Int,
    private var _userId : Int,
    private var _title : String?,
    private var _body : String?

) : RecyclerView.Adapter<CustomRecycler.ViewHolder>(){

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cust_id = itemView.findViewById<TextView>(R.id.id_custom_id)
        val cust_user_Id = itemView.findViewById<TextView>(R.id.id_custom_userId)
        val cust_title = itemView.findViewById<TextView>(R.id.id_custom_title)
        val cust_body = itemView.findViewById<TextView>(R.id.id_custom_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cust_id?.text = "ID : $_id"
        holder.cust_user_Id?.text = "User ID : $_userId"
        holder.cust_title?.text = "Title : \n $_title"
        holder.cust_body?.text = "Body : \n $_body"
    }
}