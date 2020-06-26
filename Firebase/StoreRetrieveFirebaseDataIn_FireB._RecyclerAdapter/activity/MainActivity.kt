package com.example.store_retrieved_data_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.store_retrieved_data_firebase.databinding.ActivityMainBinding


/*
*
*   // firebase recycler adapter
*   implementation 'com.firebaseui:firebase-ui-database:6.2.1'
*
* */

private const val EXTRA_NAME = "ExtraName"
private const val EXTRA_EMAIL = "ExtraEmail"
private const val EXTRA_MOBILE = "ExtraMobile"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

    }

    fun collectAndSendInfo(view: View) {

        val name = binding.idFullname.text.toString().trim()
        val email  = binding.idEmail.text.toString().trim()
        val mobile = binding.idMobileNo.text.toString().toLong()

        // I don't check users valid data , in real time application I need to do extra work for validation

        val intent = Intent(this, AddressActivity::class.java)

        intent.putExtra(EXTRA_NAME,name)
        intent.putExtra(EXTRA_EMAIL,email)
        intent.putExtra(EXTRA_MOBILE,mobile)

        startActivity(intent)
    }

    fun viewCloudData(view: View) {

        startActivity(Intent(this,ViewDataActivity::class.java))

    }
}