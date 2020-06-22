package com.example.firebaseonlinestoredata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebaseonlinestoredata.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var db  : FirebaseDatabase
    private lateinit var ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.idSaveButton.setOnClickListener {
            db = FirebaseDatabase.getInstance("https://myfirebaseonlinedata.firebaseio.com/")  // the link is (optional)
            ref = db.getReference("users")  // reference 'users' automatically create if ref 'users' not there so no need to create explicitly
            ref.setValue("This is Line 1")  // data automatically store in 'users' ref.
            ref.setValue("This is Line 2")  // previous data will replace with this data
            ref.setValue("This is Line 3")  // previous data will replace with this data & this is last data hence we have this single data only


            // we create new reference hence new child has been created
            val ref2 = db.getReference("DemoUser")
            ref2.setValue("Demo User Line 1")


            // same here we created new reference hence new child has been created
            val ref3 = db.getReference("OtherUsers")
            ref3.setValue("Other User Line 1")

        }
    }
}