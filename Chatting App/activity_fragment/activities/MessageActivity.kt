package com.example.chattingappdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.chattingappdemo.*
import com.example.chattingappdemo.R
import com.example.chattingappdemo.databinding.ActivityMessageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MessageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMessageBinding
    private lateinit var fireUser : FirebaseUser
    private lateinit var fireRef : DatabaseReference

    private lateinit var adapter: MessageAdapter
    private lateinit var seenListener: ValueEventListener

    private lateinit var uid : String
    private var userChat = ArrayList<ChatClass>()

    //private var onOffStatus : String? = null


    private var onOffStatus = MutableLiveData<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message)
        uid = intent.getStringExtra("uid")

        fireUser = FirebaseAuth.getInstance().currentUser!!
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")

        setSupportActionBar(binding.idToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        binding.idToolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        binding.idToolbar.setNavigationOnClickListener {
            finish()
        }

        onOffStatus.observe(this, Observer {
            onOffStatus()
        })
        fireRef.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) { Log.e("AXE", "Msg error : $error") }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(NewUserInfo::class.java)
                binding.idUserMsgDp.setImageResource(R.mipmap.ic_default_dp)
                binding.idMsgUsername.text = user?.name
                onOffStatus.value = user?.status
                readMsg(fireUser.uid,uid)
            }
        })

//        seenMsg()

        binding.floatingActionButton.setOnClickListener {
            val msg = binding.idUserMsgBox.text.toString().trim()
            if (msg.isNotEmpty()) {

                sendMessage(fireUser.uid, msg, uid)
                binding.idUserMsgBox.setText("")
            } else {
                Toast.makeText(this, "Unable to send Empty Message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }

    private fun sendMessage(sender: String?, msg: String, receiver : String?) {
        FirebaseDatabase
            .getInstance()
            .getReference("Chats").push()
            .setValue(ChatClass(sender,msg,receiver,"NO"))
    }

    private fun readMsg(senderId : String, receiverId : String){

        FirebaseDatabase.getInstance().getReference("Chats")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error to REad : $error")}

                override fun onDataChange(snapshot: DataSnapshot) {
                    userChat.clear()
                    for(data in snapshot.children){
                        val chat = data.getValue(ChatClass::class.java)

                        if(chat?.sender == senderId && chat.receiver == receiverId || chat?.sender == receiverId && chat.receiver == senderId){
                            //Log.e("AXE","chat =====>::>>>  ${chat.message}")
                            lastMessage = chat.message
                            userChat.add(chat)
                            seenMsg(fireUser.uid,uid)
                        }

                        adapter = MessageAdapter(userChat)
                        binding.idChatRecycler.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    Log.e("AXE","Last Message ===> : $lastMessage")
                }
            })
    }


    private fun seenMsg(senderId : String, receiverId : String){
        fireRef = FirebaseDatabase.getInstance().getReference("Chats")
        seenListener = fireRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    val chats = data.getValue(ChatClass::class.java)
                    if(chats?.sender == senderId && chats.receiver == receiverId || chats?.sender == receiverId && chats.receiver == senderId){


                        FirebaseDatabase.getInstance().getReference("Chats").child(data?.key!!).child("msgSeen").setValue("YES")
                    }
                }
            }
        })
    }

    private fun status(status : String){
        FirebaseDatabase.getInstance().getReference("NewUser").child(fireUser.uid).child("status").setValue(status)
    }

    private fun onOffStatus(){
        if(onOffStatus.value.equals("online")){
            binding.idOnOffStatus.text = onOffStatus.value
            binding.idOnOffStatus.visibility = View.VISIBLE
        }else{
            binding.idOnOffStatus.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        status("online")
        onOffStatus()
    }

    override fun onResume() {
        super.onResume()
        status("online")
        onOffStatus()
    }

    override fun onDestroy() {
        super.onDestroy()
        status("offline")
        onOffStatus()
        fireRef.removeEventListener(seenListener)
    }
}