package com.example.firebase_auth_savetoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {


    private var generateTokenButton : Button? = null
    private var tokenText : TextView?  = null
    private var saveTokenButton  :Button? = null
    private var tokenString : String? = null

    private var mAuth : FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateTokenButton = findViewById(R.id.id_generateToken)
        tokenText = findViewById(R.id.id_tokenText)
        saveTokenButton = findViewById(R.id.id_saveButton)

        mAuth = FirebaseAuth.getInstance()
        saveTokenButton?.isEnabled = false

        generateTokenButton?.setOnClickListener {

            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){
                        tokenText?.text = task.result?.token
                        tokenString = tokenText?.text.toString()

                        generateTokenButton?.isEnabled = false
                        saveTokenButton?.isEnabled = true

                    }else{
                        tokenText?.text = "Sorry, Token is not Generate"
                    }

                }

        }

        saveTokenButton?.setOnClickListener {
            //Toast.makeText(this,"save button clicked",Toast.LENGTH_LONG).show()

            val refrence = FirebaseDatabase.getInstance().getReference("users")
            refrence.child(mAuth?.currentUser!!.uid)
                .setValue(tokenString)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful){
                        tokenText?.text = "Token Save, Go to Firebase Website to See your Token"
                    }

                }

        }

    }

}