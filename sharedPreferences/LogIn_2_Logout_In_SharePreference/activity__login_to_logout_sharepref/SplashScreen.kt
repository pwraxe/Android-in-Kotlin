package com.example.login_to_logout_sharepref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.login_to_logout_sharepref.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private var binding : ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val animation = AnimationUtils.loadAnimation(this,R.anim.rotate)
        binding?.idSplashRotate?.startAnimation(animation)

        Handler().postDelayed(Runnable {

            val pref = getSharedPreferences("Login", Context.MODE_PRIVATE)
            val user = pref.getString("username",null)
            val pass = pref.getString("password",null)

            if(user == null && pass == null){
                startActivity(Intent(this@SplashScreen,Login::class.java))
            }else{
                startActivity(Intent(this@SplashScreen,HomePage::class.java))
            }
            finish()
        },3000)




    }
}
