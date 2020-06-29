package com.example.phoneauth_withvalidation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.phoneauth_withvalidation.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.NumberFormatException
import kotlin.properties.Delegates

private const val EXTRA_MOBILE = "mobile_number"
private const val USER_EXISTS = "userExists"        //1
private const val USER_NOT_EXISTS = "userNOTExists"     // 0

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var fireRef : DatabaseReference

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        fireRef = FirebaseDatabase.getInstance().getReference("Users Data")

    }

    fun checkInputAndSendOTP(view: View) {

        try {

            val plusWithCountryCode = binding.idCountryCode.selectedCountryCodeWithPlus
            val countryCode = binding.idCountryCode.selectedCountryCode
            val mobileNo = binding.idMobileNo.text.toString().toLong()

            if(mobileNo.toString().length != getLength(countryCode.toInt())){
                binding.idMobileNo.error = "Mobile No Should be ${getLength(countryCode.toInt())} digits"
            }else{

                // check in firebase whether mobile No. exists or not
                //if(exists) navigate to otp activity else navigate to registration activity

                fireRef.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(error: DatabaseError) { Log.e("AXE","Error : $error") }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot.exists()) {

                            for (data in snapshot.children) {

                                if (data.key == "$plusWithCountryCode$mobileNo") {

                                    val intent = Intent(applicationContext,OTPActivity::class.java)
                                    intent.putExtra(EXTRA_MOBILE,"$plusWithCountryCode${mobileNo}")
                                    intent.putExtra(USER_EXISTS,1 )
                                    userExistence = 1
                                    startActivity(intent)
                                    finish()

                                    break
                                }else{
                                    val intent = Intent(applicationContext,RegistrationActivity::class.java)
                                    intent.putExtra(EXTRA_MOBILE,mobileNo)
                                    userExistence = 0
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }
                    }
                })

            }
        }catch (nfe : NumberFormatException){
            binding.idMobileNo.error = "Please Enter Mobile No"
            return
        }
    }


    fun registerFreshUser(view: View) {
        startActivity(Intent(this,RegistrationActivity::class.java))
        finish()
    }
}