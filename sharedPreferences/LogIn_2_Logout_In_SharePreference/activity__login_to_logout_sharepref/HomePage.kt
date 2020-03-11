package com.example.login_to_logout_sharepref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_to_logout_sharepref.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    var binding : ActivityHomePageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idLogout?.setOnClickListener {

            val pref = getSharedPreferences("Login", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.clear()
            editor?.apply()
            startActivity(Intent(this@HomePage,Login::class.java))
            finish()
        }

    }
}
