package com.example.notificationpractice


import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class MainActivity : AppCompatActivity() {

    private var emailBox : EditText? = null
    private var passBox : EditText? = null
    private var signUpButton : Button? = null
    private var progressBar : ProgressBar? = null


    private var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        emailBox = findViewById(R.id.id_email)
        passBox = findViewById(R.id.id_password)
        signUpButton = findViewById(R.id.id_signUp)
        progressBar = findViewById(R.id.id_progress)
        progressBar?.isVisible = false

        mAuth= FirebaseAuth.getInstance()

        signUpButton?.setOnClickListener{

            val email = emailBox?.text.toString().trim()
            val password = passBox?.text.toString().trim()

            if(email.isEmpty()){
                emailBox?.error = "Email is Mandatory"
                emailBox?.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()) {
                passBox?.error = "Password is Mandatory"
                passBox?.requestFocus()
                return@setOnClickListener
            }
            progressBar?.isVisible = true
            mAuth?.createUserWithEmailAndPassword(email,password)?.addOnCompleteListener { task ->

                if(task.isSuccessful){
                    startProfileActivity()              // new user created, no previous data is in DB

                }else{
                    if(task.exception is FirebaseAuthUserCollisionException ){      // user already in database

                        mAuth?.signInWithEmailAndPassword(email,password)!!
                            .addOnCompleteListener { task ->

                                if(task.isSuccessful){
                                    startProfileActivity()
                                }else{
                                    progressBar?.isVisible = false
                                }
                        }

                    }else{
                        progressBar?.isVisible = false
                        Toast.makeText(this@MainActivity,task.exception?.message,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun startProfileActivity() {
        val intent = Intent(this,ProfileActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}


