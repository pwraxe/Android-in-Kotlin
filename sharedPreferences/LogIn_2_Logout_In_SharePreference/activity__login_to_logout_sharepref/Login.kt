package com.example.login_to_logout_sharepref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.login_to_logout_sharepref.databinding.ActivityLoginBinding
import org.w3c.dom.Text

class Login : AppCompatActivity() {


    private var binding : ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idButton?.setOnClickListener {

            val username = binding?.idUsername?.text?.toString()?.trim()
            val password = binding?.udPassword?.text?.toString()?.trim()

            if(TextUtils.isEmpty(username)){
                binding?.idUsername?.error = "Please Enter Username"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)){
                binding?.udPassword?.error = "Please Enter Password"
                return@setOnClickListener
            }
            val pref = getSharedPreferences("Login", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor?.putString("username",username)
            editor?.putString("password",password)
            editor?.apply()         // new
            // editor?.commit()     // old

            startActivity(Intent(this@Login,HomePage::class.java))
            finish()
        }



    }
}
