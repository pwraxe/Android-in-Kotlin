package com.example.chattingappdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.chattingappdemo.CustomRecyclerAdapter
import com.example.chattingappdemo.NewUserInfo
import com.example.chattingappdemo.R
import com.example.chattingappdemo.currentUser
import com.example.chattingappdemo.databinding.ActivityHomeBinding
import com.example.chattingappdemo.fragments.ChatFragment
import com.example.chattingappdemo.fragments.ProfileFragment
import com.example.chattingappdemo.fragments.UserFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var fireAuth : FirebaseAuth
    private lateinit var fireRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        fireAuth = FirebaseAuth.getInstance()
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")

        getCurrentUserInfo()        // this fun get current user data from firebase

        setSupportActionBar(binding.idToolbar)
        title = "Chat App"
        val viewPager = binding.idViewPager

        viewPager.adapter = SectionPagerAdapter(supportFragmentManager)
        binding.idTabLayout.setupWithViewPager(viewPager)

    }

    private fun getCurrentUserInfo() {
        FirebaseDatabase
            .getInstance()
            .getReference("NewUser")
            .child(fireAuth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    currentUser = snapshot.getValue(NewUserInfo::class.java)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return true
    }


    fun loggedOutUser(item: MenuItem) {
        fireAuth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun status(status : String){
        val statusMap = HashMap<String,String>()
        statusMap["status"] = status
        fireRef.child(fireAuth.currentUser?.uid.toString()).child("status")
            .setValue(status)

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

class SectionPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){
    
    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> ChatFragment()
            1 -> UserFragment()
            else -> ProfileFragment()
        }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? =
        when(position) {
            0 -> "Chat"
            1 -> "Users"
            else -> "Profile"
        }
}