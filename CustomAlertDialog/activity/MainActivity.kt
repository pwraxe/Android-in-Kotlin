package com.example.customalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private var loginButton : Button? = null
    private var userBox : EditText? = null
    private var passBox : EditText? = null

    var closeDialog : AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.loginButton_id)

        loginButton?.setOnClickListener {

            val dialog = AlertDialog.Builder(this)

            val customView = layoutInflater.inflate(R.layout.login_dialog,null)
            dialog.setView(customView)
            dialog.setPositiveButton("Login"){ d,_->
                closeDialog = d as AlertDialog?
            }
            val customDialog = dialog.create()
            customDialog.show()

            userBox = customView.findViewById(R.id.username_id)
            passBox = customView.findViewById(R.id.password_id)

            val button = customDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button?.setOnClickListener {

                val user = userBox?.text.toString()
                val pass = passBox?.text.toString()

                Toast.makeText(applicationContext,"$user : $pass",Toast.LENGTH_LONG).show()
                customDialog.dismiss()
            }
        }
    }
}
