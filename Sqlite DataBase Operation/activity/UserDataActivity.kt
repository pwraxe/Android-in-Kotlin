package com.example.databaseoperation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UserDataActivity : AppCompatActivity() {


    private var user_id: EditText? = null
    private var user_fname: EditText? = null
    private var user_lname: EditText? = null
    private var user_email: EditText? = null
    private var user_mobile: EditText? = null
    private var user_qualification: EditText? = null
    private var button_submit: Button? = null

    val helper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        user_id = findViewById(R.id.id_user_id)
        user_fname = findViewById(R.id.id_user_fname)
        user_lname = findViewById(R.id.id_user_lname)
        user_email = findViewById(R.id.id_user_email)
        user_mobile = findViewById(R.id.id_user_mobile)
        user_qualification = findViewById(R.id.id_user_qualification)
        button_submit = findViewById(R.id.id_submit_button)


        button_submit?.setOnClickListener {

        val id = user_id?.text.toString().toInt()
        val fname = user_fname?.text.toString()
        val lname = user_lname?.text.toString()
        val email = user_email?.text.toString()
        val mobile = user_mobile?.text.toString().toLong()
        val quali = user_qualification?.text.toString()

        val isInsert =helper.insertData(id,fname,lname,email,mobile,quali)
        if (isInsert) {
            Toast.makeText(this, "Data Insert", Toast.LENGTH_LONG).show()
            finish()
        }
        else
            Toast.makeText(this,"Error to Insert", Toast.LENGTH_LONG).show()
        }
    }
}
