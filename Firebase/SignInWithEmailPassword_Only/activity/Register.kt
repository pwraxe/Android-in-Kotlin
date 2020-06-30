package com.example.signinwithemailpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.signinwithemailpassword.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var fireAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)

        fireAuth = FirebaseAuth.getInstance()

        binding.idShowPassCheck.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                binding.idPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.idConfirmPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }else{
                binding.idPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.idConfirmPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    fun registerNewUser(view: View) {

        val email = binding.idEmail.text.toString().trim()
        val pass = binding.idPass.text.toString()
        val confirmPass = binding.idConfirmPass.text.toString()
        if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty() ){

            if(pass == confirmPass){
                // next

                if(pass.length >= 6 || confirmPass.length >= 6){
                    fireAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener {

                            val authResult = it.result

                            if(it.isSuccessful){

                                val extraInfo = authResult?.additionalUserInfo
                                CurrentUser.currentUser = fireAuth.currentUser
                                Log.e("ASP","Success")
                                startActivity(Intent(this,Welcome::class.java))
                                finish()

                            } else {
                                Log.e("ASP","-====> exception : ${it.exception}")
                            }
                        }
                        .addOnFailureListener {
                            Log.e("AXE","--***==> Register Exception : $it")
                        }
                }else{
                    binding.idPass.error = "Password Should more than 6 character"
                    binding.idConfirmPass.error = "Confirm Password Should more than 6 character"
                }

            }else{
                binding.idPass.error = "Password Not Match"
                binding.idConfirmPass.error = "Confirm Password Not Match"
                return
            }
        }else{

            binding.idEmail.error = "Please Enter Valid Email Address"
            binding.idEmail.requestFocus()

            binding.idPass.error = "Password Required"
            binding.idPass.requestFocus()

            binding.idConfirmPass.error = "Confirm Password Required"
            binding.idConfirmPass.requestFocus()
            return
        }
    }


    fun userExists(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}