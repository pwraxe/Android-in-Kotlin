package com.example.phoneauth_withvalidation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.phoneauth_withvalidation.databinding.ActivityOTPBinding
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

private const val EXTRA_EMAIL = "user_email"
private const val EXTRA_NAME = "user_name"
private const val EXTRA_MOBILE = "mobile_number"

class OTPActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOTPBinding
    private lateinit var fireRef : DatabaseReference


    private var name : String? = null
    private var email : String? = null
    private var mobileNo : String? = null

    private lateinit var systemCode : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_o_t_p)
        fireRef = FirebaseDatabase.getInstance().getReference("Users Data")

        name = intent.getStringExtra(EXTRA_NAME)
        email = intent.getStringExtra(EXTRA_EMAIL)
        mobileNo = intent.getStringExtra(EXTRA_MOBILE)


        when(userExistence){

            // new user
            0 -> {
                binding.idOtpMsg.text = "OTP send Successfully to $mobileNo"
                generateOTP(mobileNo!!)
                binding.idPinView.setOnCompleteListener { completed, pinResults ->
                    if(completed){
                        verifyCode(pinResults)
                    }
                }
            }

            // old user
            1 -> {

                binding.idOtpMsg.text = "OTP send Successfully to $mobileNo"
                generateOTP(mobileNo!!)

                binding.idPinView.setOnCompleteListener { completed, pinResults ->
                    if(completed){
                        verifyCode(pinResults)
                    }
                }
            }
        }
    }


    private fun generateOTP(mobileNo : String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mobileNo, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
    }


    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        override fun onCodeSent(code : String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(code, p1)
            systemCode = code
        }

        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            val smsCode = p0.smsCode
            if(smsCode != null)
                verifyCode(smsCode)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.e("AXE","Exception : $p0")
        }
    }


    private fun verifyCode(smsCode : String){
        val credential = PhoneAuthProvider.getCredential(systemCode,smsCode)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->

                when {
                    task.isSuccessful -> {
                        if(userExistence == 0){
                            // store user data in database
                            // move to welcome activity
                            Log.e("AXE","User Not Exist")
                            fireRef.child(mobileNo!!).setValue(UserData(name,email,mobileNo!!.toLong()))
                                .addOnSuccessListener {
                                    Toast.makeText(applicationContext,"Thanks for register with us",Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(applicationContext,WelcomeActivity::class.java))
                                    finish()
                                }
                                .addOnFailureListener {
                                    Log.e("AXE","Error : $it")
                                }
                        }else{
                            // user exists in db move to welcome activity
                            startActivity(Intent(applicationContext,WelcomeActivity::class.java))
                            finish()
                        }
                    }

                    task.exception is FirebaseException -> {
                        Log.e("AXE","Firebase Exception  : ${task.exception}")
                    }
                    task.isCanceled -> {
                        Log.e("AXE","Cancelled ")
                    }
                    task.isComplete -> {
                        startActivity(Intent(applicationContext,WelcomeActivity::class.java))
                        Log.e("AXE","Completed")
                        finish()
                    }
                    else -> {
                        Log.e("AXE","None of the above")
                    }
                }


            }
            .addOnFailureListener {
                Log.e("AXE","Failure : $it ")
            }
    }


    fun loginUser(view: View) {
        verifyCode(binding.idPinView.pinResults)
    }
}