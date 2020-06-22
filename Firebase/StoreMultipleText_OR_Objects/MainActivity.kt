package com.example.firebaseonlinestoredata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebaseonlinestoredata.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var db  : FirebaseDatabase
    private lateinit var ref : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.idSaveButton.setOnClickListener {

            val cId = binding.idUserID.text.toString().toInt()
            val name = binding.idUserName.text.toString().trim()
            val email = binding.idUserEmail.text.toString().trim()
            val mobile = binding.idUserMobile.text.toString().toLong()

            db = FirebaseDatabase.getInstance("https://myfirebaseonlinedata.firebaseio.com/")
            ref = db.getReference("myClients")



            //if you want to store multiple data in single child then write this line
            //ref.child("Client").push().setValue(name)           // push single text to Client child

            ref.child("Client").push().setValue(ClientDetails(cId,name,email,mobile))   // Store/Push single object





        }
    }
}

data class ClientDetails(

    var cId : Int,
    var cName : String,
    var cEmail : String,
    var cMobile : Long

)