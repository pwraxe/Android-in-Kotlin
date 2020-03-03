package com.example.databaseoperation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {


    val helper = DatabaseHelper(this)

    private var userID : EditText? = null
    private var updateFirstName : EditText? = null
    private var updateLastName : EditText? = null
    private var updateEmail : EditText? = null
    private var updateMobile : EditText? = null
    private var updateQuali : EditText? = null
    private var updateButton : Button? = null

    private var ID : String? = null
    private var FIRST : String? = null
    private var LAST : String? = null
    private var EMAIL : String? = null
    private var MOBILE : String? = null
    private var QUALI : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        val intent = intent
        val id = intent.getIntExtra("_ID",0)


        userID = findViewById(R.id.id_update_user_id)
        updateFirstName = findViewById(R.id.id_update_user_fname)
        updateLastName = findViewById(R.id.id_update_user_lname)
        updateEmail = findViewById(R.id.id_update_user_email)
        updateMobile = findViewById(R.id.id_update_user_mobile)
        updateQuali = findViewById(R.id.id_update_user_qualification)
        updateButton = findViewById(R.id.id_update_button)

        userID?.isEnabled = false
        userID?.setText(id.toString())


        val cursor = helper.getDataFromID(id)

        while (cursor!!.moveToNext())
        {

            ID = cursor.getInt(0).toString()
            FIRST = cursor.getString(1)
            LAST = cursor.getString(2)
            EMAIL = cursor.getString(3)
            MOBILE = cursor.getString(4)
            QUALI = cursor.getString(5)

            updateFirstName?.setText(FIRST)
            updateLastName?.setText(LAST)
            updateEmail?.setText(EMAIL)
            updateMobile?.setText(MOBILE)
            updateQuali?.setText(QUALI)
        }

        updateButton?.setOnClickListener {

            FIRST = updateFirstName?.text.toString()
            LAST = updateLastName?.text.toString()
            EMAIL = updateEmail?.text.toString()
            MOBILE = updateMobile?.text.toString()
            QUALI = updateQuali?.text.toString()

            val isUpdate = helper.updateData(id, FIRST!!,LAST!!,EMAIL!!,MOBILE!!.toLong(),QUALI!!)
            if(isUpdate)
            {
                Toast.makeText(this,"Data Updated", Toast.LENGTH_LONG).show()
                finish()
            }

            else
                Toast.makeText(this,"Error to Update Data", Toast.LENGTH_LONG).show()

        }
    }
}
