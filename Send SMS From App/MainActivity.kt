package com.kichenbaba.testsend_sms

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.PendingIntent

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.kichenbaba.testsend_sms.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var permissions = arrayOf(
        Manifest.permission.SEND_SMS,
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.READ_SMS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        checkForPermission()

        binding.idButton.setOnClickListener {
            sendSms()
        }
    }


    private fun sendSms(){
        try {
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            val pi = PendingIntent.getActivity(applicationContext, 0, intent, 0)
            val sms: SmsManager = SmsManager.getDefault()
            sms.sendTextMessage("96******37", null, "Test SMS From APP", null, null)

            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        }catch (ex: Exception){
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            Log.e("AXE","Exception  : $ex")
        }

    }


    private fun checkForPermission() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,permissions.toString()) != PackageManager.PERMISSION_GRANTED) {
                requestAgainForPermission()
            }
        } else {
            Toast.makeText(this, "You are using lowest version than MARSHMELLO please upgrade OS", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestAgainForPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,permissions.toString())) {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Permission")
                .setMessage("Permission require for sending SMS")
                .setPositiveButton("OK"){_,_->
                    ActivityCompat.requestPermissions(this,permissions,1234)
                }
                .setNegativeButton("Cancel"){d,_->
                    d.dismiss()
                }
                .show()

        } else {
            ActivityCompat.requestPermissions(this,permissions,1234)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1234)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You Can Now Send SMS", Toast.LENGTH_SHORT).show()
            } else {
                checkForPermission()  //ask permission till allow
            }
        }
    }

}