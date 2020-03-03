package com.example.axebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Profile : AppCompatActivity() {


    val helper = DatabaseHelper(this)
    var buffer : StringBuffer? = null




    private var customerId : Int = 0
    private var Fname : String? = null
    private var Lname : String? = null
    private var Email : String? = null
    private var ifscCode : String? = null
    private var AccountNo : String? = null
    private var Mobile : String? = null
    private var Area : String? = null
    private var Town : String? = null
    private var city : String? = null
    private var State : String? = null
    private var Pincode : String? = null
    private var Password : String? = null
    private var Mpin : String? = null
    private var TC : String? = null

    private var customerIdText : TextView? = null
    private var fnameText : TextView? = null
    private var lnameText : TextView? = null
    private var emailText : TextView? = null
    private var ifscText : TextView? = null
    private var accountText : TextView? = null
    private var mobileText : TextView? = null
    private var areaText : TextView? = null
    private var townText : TextView? = null
    private var cityText : TextView? = null
    private var stateText : TextView? = null
    private var pincodeText : TextView? = null
    private var passwordText : TextView? = null
    private var mPinText : TextView? = null
    private var TC_Text : TextView? = null

    private var deleteButtonIcon : ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val intent = intent
        customerId = intent.getIntExtra("CID",0)

        customerIdText = findViewById(R.id.profile_set_cid_id)
        fnameText = findViewById(R.id.profile_set_fname_id)
        lnameText =  findViewById(R.id.profile_set_lname_id)
        emailText =  findViewById(R.id.profile_set_email_id)
        ifscText =  findViewById(R.id.profile_set_ifsc_id)
        accountText =  findViewById(R.id.profile_set_accNo_id)
        mobileText =  findViewById(R.id.profile_set_mobile_id)
        areaText =  findViewById(R.id.profile_set_area_id)
        townText =  findViewById(R.id.profile_set_town_id)
        cityText =  findViewById(R.id.profile_set_city_id)
        stateText =  findViewById(R.id.profile_set_state_id)
        pincodeText =  findViewById(R.id.profile_set_pincode_id)
        passwordText =  findViewById(R.id.profile_set_password_id)
        mPinText =  findViewById(R.id.profile_set_mpin_id)
        TC_Text =  findViewById(R.id.profile_set_tc_id)

        deleteButtonIcon  =findViewById(R.id.delete_button_id)

        val cursor = helper.getAllData(customerId)
        buffer = StringBuffer()
        while (cursor!!.moveToNext())
        {
            customerId = cursor.getInt(0)
            Fname = cursor.getString(1)
            Lname = cursor.getString(2)
            Email = cursor.getString(3)
            ifscCode = cursor.getString(4)
            AccountNo = cursor.getString(5)
            Mobile = cursor.getString(6)
            Area = cursor.getString(7)
            Town = cursor.getString(8)
            city = cursor.getString(9)
            State = cursor.getString(10)
            Pincode = cursor.getString(11)
            Password = cursor.getString(12)
            Mpin = cursor.getString(13)
            TC = cursor.getString(14)


            customerIdText?.text = customerId.toString()
            fnameText?.text = Fname
            lnameText?.text = Lname
            emailText?.text = Email
            ifscText?.text = ifscCode
            accountText?.text = AccountNo
            mobileText?.text = Mobile
            areaText?.text = Area
            townText?.text = Town
            cityText?.text = city
            stateText?.text = State
            pincodeText?.text = Pincode
            passwordText?.text = Password
            mPinText?.text = Mpin
            if(TC?.toInt() == 1)
                TC_Text?.text = "Accepted"
            else
                TC_Text?.text = "Not Accepted"



        }


        deleteButtonIcon?.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Account?")
            builder.setMessage("Are You Sure, You want to Delete this Account? ")
            builder.setPositiveButton("Yes"){_,_ ->
                helper.deleteAccount(customerId)
                startActivity(Intent(this,MainActivity::class.java))
                this.finish()

            }
            builder.setNegativeButton("No"){dialog,_ ->
                dialog.cancel()

            }
            builder.show()
        }




    }
}
