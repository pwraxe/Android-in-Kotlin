package com.example.facebooklogindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.facebooklogindemo.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

class Welcome : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var fireAuth  : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        fireAuth = FirebaseAuth.getInstance()


        Glide.with(this).load(fireAuth.currentUser?.photoUrl).into(binding.idUserProfilePic)
        binding.idUserDisplayName.text = fireAuth.currentUser?.displayName
        binding.idEmail.text = fireAuth.currentUser?.email



    }

    fun logUserOut(view: View) {

        fireAuth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }
}