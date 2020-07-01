package com.example.facebooklogindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.facebooklogindemo.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var callbackManager : CallbackManager

    private lateinit var fireAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        fireAuth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()
        binding.loginButton.setReadPermissions("email","public_profile")
        binding.loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                handleFacebookAccessToken(result?.accessToken)
            }

            override fun onCancel() {}

            override fun onError(error: FacebookException?) {
                Log.e("AXe","FB Exception  : $error")
            }
        })

    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {

        val credential = FacebookAuthProvider.getCredential(accessToken?.token.toString())
        fireAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val user = fireAuth.currentUser
                    startActivity(Intent(this,Welcome::class.java))
                    finish()

                }else{
                    Log.e("AXE","Error : ${task.exception}")
                }
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode,resultCode,data)
    }


    override fun onStart() {
        super.onStart()
        if(fireAuth.currentUser != null){
            startActivity(Intent(this,Welcome::class.java))
            finish()
        }
    }
}