package com.example.appname

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.uploadimageonfirebase.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    /*
    *
    * Don't forget Internet Permission
    * Do needful for initialisation of firebase
    *
    */

    private lateinit var binding : ActivityMainBinding

    //to store &  retrieve text data
    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        db = FirebaseDatabase.getInstance("https://uploadimageonfirebase-11386.firebaseio.com/")
        ref = db.getReference("Users")

    }

    fun retrieveData(view: View){
    
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("AXE","Fail to Read Value ==> $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (data in snapshot.children){

                    val id = data.child("id").getValue(Int::class.java)?.toInt()
                    val name = data.child("name").getValue(String::class.java)
                    val email = data.child("email").getValue(String::class.java)
                    val mobile = data.child("mobile").getValue(Long::class.java)

                    Log.e("AXE","===>  $id | $name | $email | $mobile")
                }
            }
        })
    }
}
