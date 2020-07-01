package com.example.anonymoussignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.anonymoussignin.databinding.ActivityMainBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var fireAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        fireAuth = FirebaseAuth.getInstance()




    }

    override fun onStart() {
        super.onStart()
        if(fireAuth.currentUser != null){
            binding.idResultText.text = "You already logged in"
            binding.idLogoutButton.visibility = View.VISIBLE
        }else{
            binding.idResultText.text = "_"
        }
    }


    fun signInAnonym(view: View) {

        fireAuth.signInAnonymously()
            .addOnSuccessListener {
                binding.idResultText.text = "You just sign in anonymously"
                binding.idLinkEmailButton.visibility = View.VISIBLE
            }
    }

    fun linkWithEmail(view: View) {
        binding.idUserEmail.visibility = View.VISIBLE
        binding.idUserPassword.visibility = View.VISIBLE

        val email = binding.idUserEmail.text.toString().trim()
        val pass = binding.idUserPassword.text.toString().trim()

        if(email.isNotEmpty() && pass.length > 6){
            val emailCredential = EmailAuthProvider.getCredential(email,pass)
            fireAuth.currentUser?.linkWithCredential(emailCredential)
                ?.addOnCompleteListener {
                    if(it.isSuccessful){
                        binding.idResultText.text = "You linked with Email, Now"
                    }else{
                        binding.idResultText.text = "Exception : ${it.exception}"
                    }
                }

        }else{

            binding.idUserEmail.error = "Please Enter Email ID"
            binding.idUserEmail.requestFocus()

            binding.idUserPassword.error = "Password should min 6 char long"
            binding.idUserPassword.requestFocus()
            return
        }
    }

    fun logoutUser(view: View) {

        fireAuth.signOut()
        binding.idResultText.text = "You just sign Out"
        binding.idLinkEmailButton.visibility = View.VISIBLE
    }

}