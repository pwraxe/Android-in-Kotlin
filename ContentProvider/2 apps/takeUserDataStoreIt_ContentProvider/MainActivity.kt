 package com.example.customcontentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var fname : EditText? = null
    private var lname : EditText? = null
    private var email : EditText? = null
    private var mobile : EditText? = null
    private var button : Button? = null

    var helper : DatabaseHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fname = findViewById(R.id.id_first_name)
        lname = findViewById(R.id.id_last_name)
        email = findViewById(R.id.id_email_id)
        mobile = findViewById(R.id.id_mobile_num)
        button = findViewById(R.id.id_button)
        helper = DatabaseHelper(this,"MyLocalDB",null,1)

        button?.setOnClickListener {
            helper?.insertData(fname?.text.toString(), lname?.text.toString(), email?.text.toString(), mobile?.text.toString().toLong())
        }






    }
}
