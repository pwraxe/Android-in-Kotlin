package com.example.chattingappdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingappdemo.databinding.ChatLeftItemBinding
import com.example.chattingappdemo.databinding.ChatRightItemBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.chat_right_item.view.*

class MessageAdapter (var chats : ArrayList<ChatClass>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private val MSG_TYPE_LEFT = 0
    private val MSG_TYPE_RIGHT = 1

    inner class ViewHolder(chatView : View) : RecyclerView.ViewHolder(chatView){
        var msg = chatView.findViewById<TextView>(R.id.id_ChatMsg)
        var seenStatus = chatView.findViewById<ImageView>(R.id.id_sendStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return if(viewType == MSG_TYPE_RIGHT){
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_right_item,parent,false))
        }else{
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_left_item,parent,false))
        }
    }

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.msg.text = chats[position].message
        if(position == chats.size-1){
            if(chats[position].isMsgSeen == "YES") {
                holder.seenStatus.setImageResource(R.drawable.ic_seen)
            }else{
                holder.seenStatus.setImageResource(R.drawable.ic_delivered)
            }
        }else{
            holder.seenStatus.visibility = View.GONE
        }
    }

    override fun getItemViewType(position: Int): Int {
        val user = FirebaseAuth.getInstance().currentUser
        return if(chats[position].sender?.equals(user?.uid)!!){
            MSG_TYPE_RIGHT
        }else{
            MSG_TYPE_LEFT
        }
    }
}