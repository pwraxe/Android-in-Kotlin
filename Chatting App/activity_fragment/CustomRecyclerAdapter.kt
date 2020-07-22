package com.example.chattingappdemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingappdemo.activities.MessageActivity
import com.example.chattingappdemo.databinding.CustomUserRecyclerBinding


class CustomRecyclerAdapter(var context : Context?, var userList : ArrayList<NewUserInfo>,var lastMessage :ArrayList<String>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>()
{
    class ViewHolder(var binding : CustomUserRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CustomUserRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idRawName.text = userList[position].name
        if(lastMessage.size != 0){
            holder.binding.idSubMessage.text = lastMessage[position]
        }
        if(userList[position].status == "online"){
            holder.binding.idStatusDot.visibility = View.VISIBLE
        }else{
            holder.binding.idStatusDot.visibility = View.GONE
        }

        holder.binding.idSingleUser.setOnClickListener {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("uid",userList[position].uid)

            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = userList.size
}