package com.example.materialdesign_part_3

import android.graphics.Color
import android.inputmethodservice.ExtractEditText
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private var fullNameLayout: TextInputLayout? = null
    private var emailLayout: TextInputLayout? = null
    private var mobileLayout: TextInputLayout? = null
    private var passwordLayout: TextInputLayout? = null

    private var fullName: EditText? = null
    private var email: EditText? = null
    private var mobile: EditText? = null
    private var password: EditText? = null



    @RequiresApi(Build.VERSION_CODES.N_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullNameLayout = findViewById(R.id.id_fullNameLayout)
        emailLayout = findViewById(R.id.emailLayout)
        mobileLayout = findViewById(R.id.id_mobileLayout)
        passwordLayout = findViewById(R.id.id_passwordLayout)

        fullName = findViewById<TextInputEditText>(R.id.id_fullName)
        email = findViewById<ExtractEditText>(R.id.id_email)
        mobile = findViewById<EditText>(R.id.id_mobile)
        password = findViewById<AppCompatEditText>(R.id.id_passoword)

        fullName?.revealOnFocusHint = false


    }

    fun showData(view: View) {

        if (fullName?.text.toString().isEmpty()) {
            fullNameLayout?.error = "Full name required"
            fullNameLayout?.isErrorEnabled = true
            fullNameLayout?.setErrorIconDrawable(R.drawable.ic_error)
            return
        } else {
            fullNameLayout?.isErrorEnabled = false
            fullNameLayout?.boxBackgroundColor = Color.WHITE
            fullName?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)
        }

//--------------------------------------------------------------------------------------------------

        if (email?.text.toString().isEmpty()) {
            emailLayout?.error = "Email Required"
            emailLayout?.setErrorIconDrawable(R.drawable.ic_error)
            emailLayout?.isErrorEnabled = true
            emailLayout?.boxBackgroundColor = Color.GREEN
            emailLayout?.boxStrokeColor = Color.RED
            emailLayout?.helperText = "HelperText"
            emailLayout?.isCounterEnabled = true
            emailLayout?.requestFocus()
            return
        } else {
            emailLayout?.isErrorEnabled = false
            emailLayout?.boxBackgroundColor = Color.WHITE
            emailLayout?.helperText = "Valid Email Address"
            emailLayout?.isCounterEnabled = true
            email?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)
        }
//--------------------------------------------------------------------------------------------------

        if (mobile?.text.toString().isEmpty()) {

            mobileLayout?.error = "Please Enter Mobile No"
            mobileLayout?.isErrorEnabled = true
            mobileLayout?.setErrorIconDrawable(R.drawable.ic_error)
            mobileLayout?.isCounterEnabled = true
            mobileLayout?.requestFocus()
            return
        } else {
            mobileLayout?.isErrorEnabled = false
            mobileLayout?.boxBackgroundColor = Color.WHITE
            mobileLayout?.isCounterEnabled = false
            mobile?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)
        }

//--------------------------------------------------------------------------------------------------

        if (mobile?.text.toString().length != 10) {
            mobileLayout?.error = "Mobile No should 10 digit"
            mobileLayout?.isErrorEnabled = true
            mobileLayout?.isCounterEnabled = true
            mobileLayout?.setErrorIconDrawable(R.drawable.ic_error)
            mobileLayout?.requestFocus()
            return
        } else {
            mobileLayout?.isCounterEnabled = true
            mobileLayout?.isCounterEnabled = false
            mobileLayout?.boxBackgroundColor = Color.WHITE
            mobile?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)

        }

//--------------------------------------------------------------------------------------------------

        if (password?.text.toString().isEmpty()) {
            passwordLayout?.error = "password Required"
            passwordLayout?.isErrorEnabled = true
            passwordLayout?.setErrorIconDrawable(R.drawable.ic_error)
            passwordLayout?.isCounterEnabled = true
            passwordLayout?.requestFocus()
            return
        } else {
            passwordLayout?.isErrorEnabled = false
            passwordLayout?.isCounterEnabled = false
            passwordLayout?.boxBackgroundColor = Color.WHITE
            password?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)
        }

        if (password?.text.toString().length < 8) {
            passwordLayout?.error = "Password should min 8 char long"
            passwordLayout?.isErrorEnabled = true
            passwordLayout?.isCounterEnabled = true
            passwordLayout?.setErrorIconDrawable(R.drawable.ic_error)
            passwordLayout?.requestFocus()
            return
        } else {
            passwordLayout?.isErrorEnabled = false
            passwordLayout?.isCounterEnabled = false
            passwordLayout?.boxBackgroundColor = Color.WHITE
            password?.setCompoundDrawablesWithIntrinsicBounds(null, null, resources.getDrawable(R.drawable.ic_ok), null)
        }

        val data = " Full Name : ${fullName?.text.toString()}" +
                "\n Email : ${email?.text.toString()} " +
                "\n Mobile No : ${mobile?.text.toString()} " +
                "\n PinCode : ${password?.text.toString()}"
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }

}
