package com.example.chattingappdemo.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingappdemo.*
import com.example.chattingappdemo.R
import com.example.chattingappdemo.activities.MessageActivity
import com.example.chattingappdemo.databinding.CustomUserRecyclerBinding
import com.example.chattingappdemo.databinding.FragmentChatBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private lateinit var fireUser : FirebaseUser

    private lateinit var fireRef : DatabaseReference

    private lateinit var adapter : CustomRecyclerAdapter

    private var myChatters : NewUserInfo? = null

    val chatters = ArrayList<NewUserInfo>()

    val whoSendMeMsg = ArrayList<String>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat, container, false)

        fireUser = FirebaseAuth.getInstance().currentUser!!
        fireRef = FirebaseDatabase.getInstance().getReference("Chats")


        /**
         *
         * How I wrote login to get those user who send me msg OR i send them msg
         *
         * ==> first I compare my uid to all chats uid senders uid & receivers uid
         * ==> if my uid found then add sender uid & receiver uid to array
         * ==> remove all duplicates uid from array
         * ==> get all newUer compare uid to all new users uid NewUserInfo(name,email,...,uid)
         *
         * */


        fireRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                whoSendMeMsg.clear()
                for (data in snapshot.children){
                    val chatUser = data.getValue(ChatClass::class.java)

                    if(chatUser?.sender == fireUser.uid){
                        whoSendMeMsg.add(chatUser.receiver.toString())
                    }
                    if(chatUser?.receiver == fireUser.uid){
                        whoSendMeMsg.add(chatUser.sender.toString())
                    }
                }
                readChatting()
            }
        })


        return binding.root
    }

    private fun readChatting() {


        val uidList = ArrayList<String>()

        FirebaseDatabase.getInstance().getReference("NewUser")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) { Log.e("AXE","error on fetch user : $error") }

                override fun onDataChange(snapshot: DataSnapshot) {
                    chatters.clear()
                    for (data in snapshot.children){
                        val newUser = data.getValue(NewUserInfo::class.java)!!

                        for (index in whoSendMeMsg.indices){

                            if(newUser.uid == whoSendMeMsg[index]){
                                //extracting same uid's
                                uidList.add(whoSendMeMsg[index])
                            }
                        }
                    }

                    val uniqueUid = uidList.toSet().toList()        //senders

                    FirebaseDatabase.getInstance().getReference("NewUser")
                        .addValueEventListener(object : ValueEventListener{
                            override fun onCancelled(error: DatabaseError) {}

                            override fun onDataChange(snapshot: DataSnapshot) {
                                chatters.clear()
                                lastMessageList.clear()
                                for (data in snapshot.children){
                                    myChatters = data.getValue(NewUserInfo::class.java)
                                    for (index in uniqueUid.indices){
                                        if(uniqueUid[index] == myChatters?.uid){
                                            chatters.add(myChatters!!)
                                            getLastMessage(uniqueUid[index],myChatters!!.uid)
                                        }
                                    }
                                }
//
//                                adapter = CustomRecyclerAdapter(context,chatters)
//                                binding.idChatFragRecyclerView.adapter = adapter
//                                (binding.idChatFragRecyclerView.adapter as CustomRecyclerAdapter).notifyDataSetChanged()
                            }
                        })
                }
            })
    }

    private fun getLastMessage(senderId : String,receiverId : String){

        FirebaseDatabase.getInstance().getReference("Chats")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error to REad : $error")}

                override fun onDataChange(snapshot: DataSnapshot) {


                    for(data in snapshot.children){
                        val chat = data.getValue(ChatClass::class.java)

                        if(chat?.sender == fireUser.uid && chat.receiver == receiverId || chat?.sender == receiverId && chat.receiver == senderId){

                            Log.e("AXE","chat *********>>>   ${chat.message}")
                            lastMessage = chat.message
                        }
                    }
                    lastMessage?.let { lastMessageList.add(it) }

                    adapter = CustomRecyclerAdapter(context,chatters, lastMessageList)
                    binding.idChatFragRecyclerView.adapter = adapter
                    (binding.idChatFragRecyclerView.adapter as CustomRecyclerAdapter).notifyDataSetChanged()
                }
            })
    }

    private fun status(status : String){
        FirebaseDatabase.getInstance().getReference("NewUser")
            .child(fireUser.uid).child("status").setValue(status)
    }

    override fun onStart() {
        super.onStart()
        status("online")
    }

    override fun onResume() {
        super.onResume()
        status("online")
    }

    override fun onPause() {
        super.onPause()
        status("online")
    }

    override fun onDestroy() {
        super.onDestroy()
        status("offline")
    }

}