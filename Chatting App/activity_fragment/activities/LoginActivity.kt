package com.example.chattingappdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.chattingappdemo.NewUserInfo
import com.example.chattingappdemo.R
import com.example.chattingappdemo.currentUser
import com.example.chattingappdemo.databinding.ActivityMainBinding
import com.google.firebase.auth.*
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fireRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")
        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null) {
            startActivity(Intent(this,
                HomeActivity::class.java))
            finishAffinity()
        }
    }

    fun loginUser(view: View) {
        val email = binding.idEmailID.text.toString().trim()
        val password = binding.idPassword.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty()){

            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task ->

                    if(task.isSuccessful){


                        FirebaseDatabase.getInstance().getReference("NewUser/${firebaseAuth.currentUser?.uid}")
                            .addValueEventListener(object : ValueEventListener{
                            override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error to fetch Current User : $error")}

                            override fun onDataChange(snapshot: DataSnapshot) {
                                currentUser = snapshot.getValue(NewUserInfo::class.java)
                            }
                        })

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                }
                .addOnFailureListener {exception ->
                    when (exception) {
                        is FirebaseAuthUserCollisionException -> { Log.e("AXE","User Collision") }
                        is FirebaseAuthInvalidUserException -> { Log.e("AXE","Invalid User / User Not Exists") }
                        is FirebaseAuthInvalidCredentialsException -> { Log.e("AXE","Invalid Credential") }
                        is FirebaseAuthEmailException -> { Log.e("AXE","Email Exception") }
                        is FirebaseAuthWeakPasswordException -> { Log.e("AXE","week password") }
                        else -> {
                            Log.e("AXE","Exception  : $exception")
                        }
                    }
                }

        }else
            Toast.makeText(this, "All Field Mandatory", Toast.LENGTH_SHORT).show()
    }

    fun registerUser(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}