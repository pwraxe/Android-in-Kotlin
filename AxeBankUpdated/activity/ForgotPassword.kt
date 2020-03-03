package com.example.axebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

class ForgotPassword : AppCompatActivity() {


    var helper = DatabaseHelper(this)
    private var recoverText : TextView?= null
    private var recoverCustomerId : EditText? = null
    private var setPassText : CardView? = null

    private var customerId : Int = 0
    private var errorMessage : String? = "Different Password Entered"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        recoverText = findViewById(R.id.recoverText_id)
        val blink =  AnimationUtils.loadAnimation(this,R.anim.blink)
        recoverText?.startAnimation(blink)

        recoverCustomerId = findViewById(R.id.recover_customer_id)
        setPassText = findViewById(R.id.setPasswordText_id)

        setPassText?.setOnClickListener {
            customerId = recoverCustomerId?.text.toString().toInt()

            var recoveredPass : String = ""
            var id : Int
            var pass  = ""

            var isValidCustomer =helper.getPassword()
            if(isValidCustomer.count == 0)
                Toast.makeText(this,"You are Not Register With Us,Please Register You will get Your Password", Toast.LENGTH_LONG).show()
            else {
                while (isValidCustomer.moveToNext())
                {
                    id = isValidCustomer.getInt(0).toString().toInt()
                    pass = isValidCustomer.getString(1).toString()
                    if(customerId.equals(id))
                    {
                        recoveredPass = pass
                        var showPass = AlertDialog.Builder(this)
                        showPass.setTitle("Password Recovery Process... ")
                        showPass.setMessage("Your Password is : $recoveredPass")

                        showPass.setPositiveButton("OK"){_,_ ->
                            this.finish()
                        }
                        showPass.show()
                    }
                    else
                        Toast.makeText(this,"Customer ID is not available", Toast.LENGTH_LONG).show()
                }



            }





        }



    }
}
