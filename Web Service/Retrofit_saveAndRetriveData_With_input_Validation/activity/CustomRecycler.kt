package com.example.retrofit_savedata_method2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecycler(
    private var context: Context,
    private var mID : Int ,
    private var mUserID : Int,
    private var mTitle : String?,
    private var mBody : String?) : RecyclerView.Adapter<CustomRecycler.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cust_id = itemView.findViewById<TextView>(R.id.id_custom_layout_id)
        val cust_user_id = itemView.findViewById<TextView>(R.id.id_custom_layout_userId)
        val cust_title = itemView.findViewById<TextView>(R.id.id_custom_layout_title)
        val cust_body = itemView.findViewById<TextView>(R.id.id_custom_layout_body)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cust_id?.text = "ID : ${mID}"            // default id from server is 101
        holder.cust_user_id?.text = "User ID : $mUserID"
        holder.cust_title?.text = "Title :  $mTitle"
        holder.cust_body?.text = "Body : $mBody"
    }
}

/*
* Why 101 id?
*   --> becoz
*   In this website -->https://jsonplaceholder.typicode.com/posts
*
*   there are 100 pre-define objects
*   in this program you are passing 1 more extra object
*   this object nothing but next object of 100 i.e. 101
*   and server return this 101th object
*
* */