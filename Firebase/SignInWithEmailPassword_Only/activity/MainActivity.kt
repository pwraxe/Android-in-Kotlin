package com.example.signinwithemailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.signinwithemailpassword.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.idShowCheck.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                binding.idPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }else{
                binding.idPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    fun loginExistingUser(view: View) {

        val email = binding.idEmail.text.toString().trim()
        val password = binding.idPassword.text.toString().trim()

        if(email.isNotEmpty() && password.isNotEmpty()){

            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful) {
                        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,Welcome::class.java))
                        finish()
                    }else{

                        when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                Toast.makeText(this,"Invalid Password",Toast.LENGTH_SHORT).show()
                                return@addOnCompleteListener
                            }
                            is FirebaseAuthInvalidUserException -> {
                                Toast.makeText(this,"Invalid User",Toast.LENGTH_SHORT).show()
                                binding.idEmail.error = "Email ID Not Exists"
                                return@addOnCompleteListener
                            }
                            is FirebaseAuthUserCollisionException -> {

                                Toast.makeText(this,"user collision",Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(this,"${task.exception}",Toast.LENGTH_SHORT).show()
                                Log.e("AXE","*** Exception  :  ${task.exception}")
                            }
                        }

                        Log.e("AXE","******> : ${task.exception}")
                    }
                }

//                .addOnSuccessListener {
//
//                    val user = it.user
//                    Log.e("AXE","Describe Content :  ${user?.describeContents()}")
//                    Log.e("AXE","isAnonymous  :  A${user?.isAnonymous}")
//
//                    Log.e("AXE","Metadata  :  ${user?.metadata}")
//                    Log.e("AXE","metadata creationTimestamp  : ${user?.metadata?.creationTimestamp}")
//                    Log.e("AXE","metadata lastSignInTimestamp  :  ${user?.metadata?.lastSignInTimestamp}")
//
//                    Log.e("AXE","multiFactor  :  ${user?.multiFactor}")
//                    Log.e("AXE","multiFactor Session : ${user?.multiFactor?.session}")
//                    Log.e("AXE","multiFactor enrolledFactors  ${user?.multiFactor?.enrolledFactors}")
//
//                    Log.e("AXE","providerData : ${user?.providerData}")
//                    Log.e("AXE","providerData[0] :  ${user?.providerData?.get(0)}")
//                    Log.e("AXE","providerData size  :  ${user?.providerData?.size}")
//
//                    Log.e("AXE","display Name  :  ${user?.displayName}")
//                    Log.e("AXE","isEmailVerified  :  ${user?.isEmailVerified}")
//                    Log.e("AXE","Email  : ${user?.email}")
//                    Log.e("AXE","PhoneNo  : ${user?.phoneNumber}")
//                    Log.e("AXE","Photo URL : ${user?.photoUrl}")
//                    Log.e("AXE","Provider ID  :  ${user?.providerId}")
//                    Log.e("AXE","UID  :  ${user?.uid}")
//
//                }
//



        }
    }

    fun createNewUser(view: View) {
        startActivity(Intent(this,Register::class.java))
    }


    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if(currentUser != null){
            startActivity(Intent(this,Welcome::class.java))
            finish()
        }
    }

    fun forgotPassword(view: View) {

        val email = binding.idEmail.text.toString().trim()
        if(email.isNotEmpty()){
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    Toast.makeText(this,"link successfully send on $email",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Log.e("AXE","Email send fail Exception : $it")
                }


        }else{
            binding.idEmail.error = "Email requires for verification link"
            return
        }



    }


}