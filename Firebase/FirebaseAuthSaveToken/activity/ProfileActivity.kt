package com.example.firebase_auth_savetoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {


    private var tokenText : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tokenText = findViewById(R.id.id_token)

        val intent = intent
        val token = intent.getStringExtra("Token")
        Log.e("AXE","Token : $token")
        tokenText?.text = token

    }
}
