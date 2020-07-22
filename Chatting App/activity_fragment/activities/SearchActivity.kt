package com.example.chattingappdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.chattingappdemo.CustomRecyclerAdapter
import com.example.chattingappdemo.NewUserInfo
import com.example.chattingappdemo.R
import com.example.chattingappdemo.databinding.ActivitySearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    private lateinit var fireUser : FirebaseUser
    private lateinit var fireRef : DatabaseReference

    private lateinit var adapter : CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_search)
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")
        fireUser = FirebaseAuth.getInstance().currentUser!!


        binding.idSearchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(input : String?): Boolean {
                searchFirebaseUser(input)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchFirebaseUser(newText)
                return false
            }
        })
    }

    private fun searchFirebaseUser(keyword : String?){
        val searchedUser = ArrayList<NewUserInfo>()
        fireRef.orderByChild("name").startAt(keyword)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){

                        searchedUser.clear()
                        for (user in snapshot.children){
                            searchedUser.add(user.getValue(NewUserInfo::class.java)!!)
                        }
                        //call adapter
                        adapter = CustomRecyclerAdapter(applicationContext,searchedUser, arrayListOf())
                        binding.idSearchRecycler.adapter = adapter
                        binding.idSearchRecycler.adapter?.notifyDataSetChanged()

                    }else{
                        // gone visibility of recycler & progress show lottie
                        binding.idSearchRecycler.visibility= View.GONE
                        binding.idSearchLottie.visibility = View.VISIBLE
                    }
                }
            })
    }

}