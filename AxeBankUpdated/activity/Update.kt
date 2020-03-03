package com.example.axebank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView

class Update : AppCompatActivity() {


    var buffer = StringBuffer()

    var indianStates = arrayOf("Select State","Andhra Pradesh","Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
        "Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir", "Jharkhand","Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
        "Meghalaya","Mizoram", "Nagaland", "Odisha", "Punjab","Rajasthan", "Sikkim","Tamil Nadu", "Telangana", "Tripura", "Uttarakhand",
        "Uttar Pradesh","West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry")



    private var helper = DatabaseHelper(this)
    private var customer_id : Int = 0
    private var update_CustomerID: EditText? = null
    private var update_FirstName: EditText? = null
    private var update_LastName: EditText? = null
    private var update_Email: EditText? = null
    private var update_IfscCode : EditText? = null
    private var update_AccountNumber : EditText? = null
    private var update_MobileNo: EditText? = null
    private var update_LivingArea: EditText? = null
    private var update_LivingTown: EditText? = null
    private var update_LivingCity: EditText? = null
    private var update_LivingState: Spinner? = null
    private var update_LivingPinCode: EditText? = null
    private var update_FirstPassword: EditText? = null
    private var update_SecondPassword: EditText? = null
    private var update_Mpin: EditText? = null
    private var update_Button: CardView? = null


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


    private var spinnerItemPosition : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        var intent = intent
        customer_id = intent.getIntExtra("CID",0)
        update_CustomerID = findViewById(R.id.update_customer_id)
        update_FirstName = findViewById(R.id.update_CustomerFirstName_id)
        update_LastName = findViewById(R.id.update_CustomerLastName_id)
        update_Email = findViewById(R.id.update_CustomerEmail_id)
        update_IfscCode = findViewById(R.id.update_CustomerIFSC_Code_id)
        update_AccountNumber = findViewById(R.id.update_CustomerAccountNo_id)
        update_MobileNo = findViewById(R.id.update_CustomerMobile_id)
        update_LivingArea = findViewById(R.id.update_CustomerArea_id)
        update_LivingTown = findViewById(R.id.update_CustomerTown_id)
        update_LivingCity = findViewById(R.id.update_CustomerCity_id)
        update_LivingState = findViewById(R.id.update_CustomerState_id)
        update_LivingPinCode = findViewById(R.id.update_CustomerPincode_id)
        update_FirstPassword = findViewById(R.id.update_CustomerFirstPassword_id)
        update_SecondPassword = findViewById(R.id.update_CustomerSecondPassword_id)
        update_Mpin = findViewById(R.id.update_CustomerMPin_id)
        update_Button = findViewById(R.id.update_card_button_id)

        update_CustomerID?.isEnabled = false
        update_CustomerID?.setText(customer_id.toString())

        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, indianStates)
        update_LivingState?.adapter = stateAdapter

        val cursor = helper.getAllData(customer_id)
        buffer = StringBuffer()
        while (cursor!!.moveToNext())
        {
            customer_id = cursor.getInt(0)
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

            update_FirstName?.setText(Fname)
            update_LastName?.setText(Lname)
            update_Email?.setText(Email)
            update_IfscCode?.setText(ifscCode)
            update_AccountNumber?.setText(AccountNo)
            update_MobileNo?.setText(Mobile)
            update_LivingArea?.setText(Area)
            update_LivingTown?.setText(Town)
            update_LivingCity?.setText(city)
            val stateId = stateAdapter.getPosition("$State")
            update_LivingState?.setSelection(stateId)
            update_LivingPinCode?.setText(Pincode)
            update_FirstPassword?.setText(Password)
            update_SecondPassword?.setText(Password)
            update_Mpin?.setText(Mpin)

        }

        update_Button?.setOnClickListener {

            Fname = update_FirstName?.text.toString()
            Lname = update_LastName?.text.toString()
            Email = update_Email?.text.toString()
            ifscCode = update_IfscCode?.text.toString()
            AccountNo = update_AccountNumber?.text.toString()
            Mobile = update_MobileNo?.text.toString()
            Area = update_LivingArea?.text.toString()
            Town = update_LivingTown?.text.toString()
            city = update_LivingCity?.text.toString()
            State = update_LivingState?.selectedItem.toString()
            Pincode = update_LivingPinCode?.text.toString()
            var pass1 = update_FirstPassword?.text.toString()
            var pass2 = update_SecondPassword?.text.toString()
            if(pass1 == pass2)
                Password = pass2
            else {
                update_FirstPassword?.error = "Password Not Match"
                update_SecondPassword?.error = "Password Not Match"
            }
            Mpin = update_Mpin?.text.toString()
            val result = helper.updateCustomerData(customer_id,Fname,Lname,Email,ifscCode,AccountNo,Mobile,Area,Town,city,State,Pincode,
                Password,Mpin?.toInt())
            if(result) {   // if data update successfully update() return 1
                Toast.makeText(this, "Data Updated", Toast.LENGTH_LONG).show()
                this.finish()
            }
            else
                Toast.makeText(this,"Data Couldn't Update",Toast.LENGTH_LONG).show()

        }






    }
}
