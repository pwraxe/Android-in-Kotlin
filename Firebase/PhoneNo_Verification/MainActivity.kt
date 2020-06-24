package com.example.phoneno_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.arch.core.executor.TaskExecutor
import androidx.databinding.DataBindingUtil
import com.dpizarro.pinview.library.PinView
import com.dpizarro.pinview.library.PinViewSettings
import com.example.phoneno_authentication.databinding.ActivityMainBinding
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.hbb20.CountryCodePicker
import java.util.concurrent.TimeUnit


/*
*
* implementation 'com.hbb20:ccp:2.4.0'    ---> Country Code Picker Dependency
* implementation 'com.github.davidpizarro:pinview:1.0.0'      ===>  pinView dependency
*
* mayNeed2Add => maven { url 'https://jitpack.io' }
* app:nativePinBox="true"  ==> if true _ will appear if false box will appear
* app:numberCharacters="1"  =======> 1 character / box
*app:numberPinBoxes="6" ----> !working hence set dynamically
* Don't forgot to enabling sign-in method
*
* Don't forgot to add SHA-1 key in your firebase console
* */


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var userNumber : EditText
    private lateinit var button: Button
    private lateinit var displayNo : TextView

    private lateinit var pinView : PinView


    private lateinit var systemCode : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        countryCodePicker = binding.idCcp
        userNumber = binding.idNumberInput
        button = binding.idGetNoButton
        displayNo = binding.idDisplayNo


        pinView = binding.idPinView

        pinView.setPin(6,InputType.TYPE_CLASS_NUMBER)

        pinView.setOnCompleteListener { completed, pinResults ->

            if(completed){
                verifyCode(pinResults)
            }
        }

        button.setOnClickListener {
            getNumber()
        }
    }

    private fun getNumber(){

        val number = userNumber.text.toString().trim()

        val code =  binding.idCcp.selectedCountryCodeWithPlus.toString() //countryCodePicker.selectedCountryCodeWithPlus.toString()

        displayNo.text = "$code$number"


        if(isValidMobileNo(number)){        // number = without + & country code
            sendVerificationCodeToUser("$code$number")
        }else
            Toast.makeText(this,"Invalid Mobile No",Toast.LENGTH_LONG).show()
    }


    private fun sendVerificationCodeToUser(number : String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            number, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            TaskExecutors.MAIN_THREAD, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks

    }

    private var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

        override fun onCodeSent(code: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(code, p1)
            systemCode = code

        }

        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            val code = phoneAuthCredential.smsCode
            if(code != null){
                pinView.pinResults
                Toast.makeText(applicationContext,"Code : $code",Toast.LENGTH_LONG).show()
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(ex : FirebaseException) {
            Log.e("AXE","Exception : $ex")
        }
    }


    private fun verifyCode(code : String){
        val credential = PhoneAuthProvider.getCredential(systemCode,code)
        signInUsingPhoneAuthCredential(credential)
    }

    private fun signInUsingPhoneAuthCredential(credential: PhoneAuthCredential){

        val auth = FirebaseAuth.getInstance()


        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Mobile Number Verification Successful",Toast.LENGTH_LONG).show()

                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this,"Verification Not Succeed",Toast.LENGTH_LONG).show()
                        Log.e("AXE","Error : ${task.exception}")
                    }
                }
            }
    }

    private fun isValidMobileNo(mobileNo : String) : Boolean = if(mobileNo.length < 6 || mobileNo.length > 13 ) false else android.util.Patterns.PHONE.matcher(mobileNo).matches()

    fun manualVerification(view : View){
        val userEnteredCode = pinView.pinResults.toString()
        if(userEnteredCode.isNotEmpty()){
            verifyCode(userEnteredCode)
        }

    }
}