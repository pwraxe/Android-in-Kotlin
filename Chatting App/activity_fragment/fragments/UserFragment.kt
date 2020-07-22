package com.example.chattingappdemo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingappdemo.CustomRecyclerAdapter
import com.example.chattingappdemo.NewUserInfo
import com.example.chattingappdemo.R
import com.example.chattingappdemo.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class UserFragment : Fragment() {

    private lateinit var binding : FragmentUserBinding
    private lateinit var fireAuth : FirebaseAuth
    private lateinit var fireRef : DatabaseReference

    private var userList = ArrayList<NewUserInfo>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_user, container, false)
        fireAuth = FirebaseAuth.getInstance()
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")

        loadOnlineData()

        return binding.root
    }

    private fun loadOnlineData() {

        fireRef.addValueEventListener(object : ValueEventListener{

            override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error : $error")}

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    userList.clear()

                    for (data in snapshot.children){
                        val newUser = data.getValue(NewUserInfo::class.java)
                        if(newUser?.uid != fireAuth.uid)
                            newUser?.let { userList.add(it) }
                    }

                    binding.idProgressBar.visibility = View.GONE
                    binding.idRecyclerView.visibility = View.VISIBLE
                    binding.idLottie.visibility = View.GONE

                    binding.idRecyclerView.adapter = CustomRecyclerAdapter(context, userList, arrayListOf())
                    binding.idRecyclerView.adapter?.notifyDataSetChanged()

                }else{
                    binding.idProgressBar.visibility = View.GONE
                    binding.idRecyclerView.visibility = View.GONE
                    binding.idLottie.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun status(status : String){
        fireAuth.currentUser?.uid?.let {
            fireRef.child(it).child("status")
                .setValue(status)
        }
    }

    override fun onResume() {
        super.onResume()
        status("online")
    }

    override fun onDestroy() {
        super.onDestroy()
        status("offline")
    }
}
