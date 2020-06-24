package com.example.phoneno_authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.phoneno_authentication.databinding.ActivityMainBinding
import com.hbb20.CountryCodePicker


/*
*
* implementation 'com.hbb20:ccp:2.4.0'    ---> Country Code Picker Dependency
*
* */


class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var userNumber : EditText
    private lateinit var button: Button
    private lateinit var displayNo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        countryCodePicker = binding.idCcp
        userNumber = binding.idNumberInput
        button = binding.idGetNoButton
        displayNo = binding.idDisplayNo

        button.setOnClickListener {
            getNumber()
        }
    }

    private fun getNumber(){

        val number = userNumber.text.toString().trim()
        val code =  binding.idCcp.selectedCountryCodeWithPlus.toString() //countryCodePicker.selectedCountryCodeWithPlus.toString()

        displayNo.text = "$code$number"


    }

}