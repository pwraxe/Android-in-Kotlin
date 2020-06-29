package com.example.phoneauth_withvalidation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.phoneauth_withvalidation.databinding.ActivityRegistrationBinding

private const val EXTRA_EMAIL = "user_email"
private const val EXTRA_NAME = "user_name"
private const val EXTRA_MOBILE = "mobile_number"

private const val USER_EXISTS = "userExists"        //1
private const val USER_NOT_EXISTS = "userNOTExists"     // 0


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration)
        binding.idMobile.setText("${intent.getLongExtra(EXTRA_MOBILE,0)}")

    }

    fun registerNewUser(view: View) {

        try {
            val name = binding.idName.text.toString().trim()
            val email = binding.idEmail.text.toString().trim()
            val mobileNo = binding.idMobile.text.toString().toLong()
            val fullMobileNo = "${binding.idCountryCode.selectedCountryCodeWithPlus}${binding.idMobile.text.toString().toLong()}"

            if(name.isNotEmpty() && email.isNotEmpty() && mobileNo.toString().isNotEmpty()){

                val intent = Intent(this,OTPActivity::class.java)
                intent.putExtra(EXTRA_NAME,name)
                intent.putExtra(EXTRA_EMAIL,email)
                intent.putExtra(EXTRA_MOBILE,fullMobileNo)
                userExistence = 0           //0 mean user ! exists
                startActivity(intent)

            }else{
                binding.idName.error = "Name required"
                binding.idName.requestFocus()

                binding.idEmail.error = "Email Required"
                binding.idEmail.requestFocus()

                return
            }

        }catch (nfe : NumberFormatException){
            val countryCode = binding.idCountryCode.selectedCountryCode.toString()
            binding.idMobile.error = "please enter ${getLength(countryCode.toInt())}Mobile number"
            return
        }

    }
}