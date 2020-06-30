package com.example.signinwithemailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.signinwithemailpassword.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth

class Welcome : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    private lateinit var fireAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_welcome)

        fireAuth = FirebaseAuth.getInstance()
    }

    fun logoutUser(view: View) {
        fireAuth.signOut()
        startActivity(Intent(this,MainActivity::class.java))
    }
}