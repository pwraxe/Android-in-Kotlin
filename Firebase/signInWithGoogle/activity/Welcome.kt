package com.example.googlesignindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.googlesignindemo.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

private const val EXTRA_NAME = "userName"
private const val EXTRA_EMAIL = "userEmail"
private const val EXTRA_URL = "userUrl"

class Welcome : AppCompatActivity() {

    private lateinit var binding : ActivityWelcomeBinding

    private lateinit var fireAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        fireAuth  = FirebaseAuth.getInstance()

        Glide.with(this).load(fireAuth.currentUser?.photoUrl)
            .placeholder(R.drawable.loading_gif)
            .error(R.drawable.ic_cloud_error)
            .into(binding.idProfilePic)

        binding.idUserEmail.text = fireAuth.currentUser?.email
        binding.idUserName.text = fireAuth.currentUser?.displayName

    }

    fun signOut(view: View) {

        fireAuth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }
}