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
import com.example.chattingappdemo.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var fireRef : DatabaseReference
    private lateinit var fireAuth : FirebaseAuth

    private val liveStatus = "offline"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_register
        )
        fireRef = FirebaseDatabase.getInstance().getReference("NewUser")
        fireAuth = FirebaseAuth.getInstance()
    }

    fun registerNewUser(view: View) {

        val name = binding.idName.text.toString().trim()
        val email = binding.idEmail.text.toString().trim()
        val mobile = binding.idMobileNo.text.toString().trim()
        val password = binding.idPassword.text.toString()
        
        if(name.isEmpty() && email.isEmpty() && mobile.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "all fields mandatory", Toast.LENGTH_SHORT).show()
        }else{
            
            fireAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){

                        val uid = task.result?.user?.uid.toString()

                        fireRef.child(uid).setValue(NewUserInfo(name, email, mobile, password, task.result?.user?.uid.toString(),liveStatus))
                            .addOnCompleteListener {
                                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                                gotoHomeActivity()
                            }
                            .addOnFailureListener {
                                Toast.makeText(applicationContext, "fail : $it", Toast.LENGTH_SHORT).show()
                                Log.e("AXE","Fail : $it")
                            }

                    }else{
                        Log.e("AXE","Completion Fail : ${task.exception}")
                        return@addOnCompleteListener
                    }
                }
        }
    }

    private fun gotoHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }
}