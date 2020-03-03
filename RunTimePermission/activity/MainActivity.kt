package com.example.permissiondemo

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.security.Permission

class MainActivity : AppCompatActivity() {

    private var permissionButton : Button? = null
    private var permissions = arrayOf(Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS,Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionButton = findViewById(R.id.id_button_perm)
        permissionButton?.setOnClickListener {

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,permissions.toString()) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission already Granted", Toast.LENGTH_LONG).show()
                } else {
                    requestAgainForPermission()
                }
            } else {
                Toast.makeText(this, "You are using lowest version than MARSHMELLO please upgrade OS", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun  requestAgainForPermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,permissions.toString())) {
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Permission")
                .setMessage("Permission require for this and that")
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
        if(requestCode == 1234)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission NOT Granted", Toast.LENGTH_LONG).show()
            }
        }
    }
}
