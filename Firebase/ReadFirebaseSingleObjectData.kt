package com.example.anyappname

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
    * make sure you publish read as true and write as false in firebase console rule 
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
        
        //fetching single data
        ref.child("user2").addValueEventListener(object : ValueEventListener{     // we pass here 'user2' for getting data of 'user2' child
            override fun onCancelled(error: DatabaseError) {
                Log.e("AXE","Fail to Read Value ==> $error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val map = snapshot.value as HashMap<String,Any>

                val id = map["id"].toString().toInt()
                val name = map["name"].toString()
                val email = map["email"].toString()
                val mobile = map["mobile"].toString().toLong()

                Log.e("AXE","data --------> $id | $name | $email | $mobile")      // here we fetch single value of data

            }
        })
    }
}
