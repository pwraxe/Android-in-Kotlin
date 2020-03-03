package com.example.axebank

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {


    private var userImage : CircleImageView? = null
    private var customerId : EditText? = null
    private var customerPassword : EditText? = null
    private var forgotPass : TextView ? = null
    private var loginButton : CardView? = null
    private var registerButton : CardView? = null

    private var customerIdError : TextView? = null
    private var passwordError : TextView? = null

    private var userCustomerID : String? = ""
    private var usersPassword : String? = ""

    private var isValid : Boolean = false

    private var helper : DatabaseHelper = DatabaseHelper(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userImage = findViewById(R.id.homePAgeCircularImage_id)
        customerId = findViewById(R.id.customer_id)
        customerPassword = findViewById(R.id.customer_password_id)
        forgotPass = findViewById(R.id.forgetPasswordText_id)
        loginButton = findViewById(R.id.login_id)
        registerButton = findViewById(R.id.register_id)

        customerIdError = findViewById(R.id.HomePageCustomerID_Error_id)
        passwordError = findViewById(R.id.HomePagePassword_Error_id)

        forgotPass?.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }
        loginButton?.setOnClickListener {

            userCustomerID = customerId?.text.toString()
            usersPassword = customerPassword?.text.toString()

            if(TextUtils.isEmpty(userCustomerID.toString())) {
                customerId?.error = "Customer ID is Mandatory"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(usersPassword)){
                customerPassword?.error = "Password is Mandatory"
                return@setOnClickListener
            }

            if (userCustomerID!!.isNotEmpty() && usersPassword!!.isNotEmpty()) {

                var isIdAvailable = false
                val isValidCustomer: Cursor = helper.getCustomerIDPassword()
                if(isValidCustomer.count == 0)
                    Toast.makeText(this,"Database sounds Empty",Toast.LENGTH_LONG).show()
                else {
                    while (isValidCustomer.moveToNext())
                    {
                        val id = isValidCustomer.getInt(0).toString()
                        val pass = isValidCustomer.getString(1).toString()

                        if (id.equals(userCustomerID)) {
                            isIdAvailable = true
                            customerId?.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ok_id,0)
                            if(pass.equals(usersPassword)) {
                                isValid = true

                                customerPassword?.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ok_id,0)
                                val intent = Intent(this, HomePage::class.java)
                                intent.putExtra("ID", id.toInt())
                                startActivity(intent)
                                this.finish()
                            }
                            else{
                                customerPassword?.error = "Invalid Password"
                            }
                        } else
                            isValid = false
                    }
                    if(isIdAvailable){
                        //Toast.makeText(this,"Customer ID Found, Now enter Correct Password to Login",Toast.LENGTH_LONG).show()
                        customerId?.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ok_id,0)
                    }
                    else
                        customerId?.error = "ID not exists"


                }


            }
            else
            {
                Toast.makeText(this,"try again",Toast.LENGTH_LONG).show()
            }




        }

        registerButton?.setOnClickListener {
            startActivity(Intent(this,Registration::class.java))
        }


    }

    override fun onResume() {

        customerId?.setText("")
        customerPassword?.setText("")

        super.onResume()
    }

}

