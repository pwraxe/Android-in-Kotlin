package com.example.axebank

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream

class Registration : AppCompatActivity() {


    private val PersmissionCode = 1234
    private var ImageCapturecode = 1526
    private var image_uri: Uri? = null

    private var byteArray: ByteArray? = null
    private var isTCAccept: Boolean = false

    private var img_uri : ByteArray? = null

    private var helper: DatabaseHelper = DatabaseHelper(this)


    // find view by id and assign following variable

    private var userSelfie: CircleImageView? = null
    private var userCustomerID: EditText? = null
    private var userFirstName: EditText? = null
    private var userLastName: EditText? = null
    private var userEmail: EditText? = null
    private var userIfscCode : EditText? = null
    private var userAccountNumber : EditText? = null
    private var userMobileNo: EditText? = null
    private var userLivingArea: EditText? = null
    private var userLivingTown: EditText? = null
    private var userLivingCity: EditText? = null
    private var userLivingState: Spinner? = null
    private var userLivingPinCode: EditText? = null
    private var userFirstPassword: EditText? = null
    private var userSecondPassword: EditText? = null
    private var userMpin: EditText? = null
    private var checkBox: CheckBox? = null
    private var registerButton: CardView? = null


    // take user data and store following variable

    private var takeCustomerSelfie: Int? = null
    private var takeCustomerID: String? = null
    private var takeCustomerFirstName: String? = null
    private var takeCustomerLastName: String? = null
    private var takeCustomerEmailID: String? = null
    private var takeCustomerIfscCode : String? = null
    private var takeCustomerAccountNo : String? = null
    private var takeCustomerMobileNo: String? = null
    private var takeCustomerArea: String? = null
    private var takeCustomerTown: String? = null
    private var takeCustomerCity: String? = null
    private var takeCustomerState: String? = null
    private var takeCustomerPincode: String? = null
    private var takeCustomerFirstPassword: String? = null
    private var takeCustomerSecondPassword: String? = null
    private var takeCustomerMPIN: String? = null

    var indianStates = arrayOf("Select State","Andhra Pradesh","Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
        "Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir", "Jharkhand","Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
        "Meghalaya","Mizoram", "Nagaland", "Odisha", "Punjab","Rajasthan", "Sikkim","Tamil Nadu", "Telangana", "Tripura", "Uttarakhand",
        "Uttar Pradesh","West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Lakshadweep", "Puducherry")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        userSelfie = findViewById(R.id.register_userCircularSelfie_id)
        userCustomerID = findViewById(R.id.register_customer_id)
        userFirstName = findViewById(R.id.register_CustomerFirstName_id)
        userLastName = findViewById(R.id.register_CustomerLastName_id)
        userEmail = findViewById(R.id.register_CustomerEmail_id)
        userIfscCode = findViewById(R.id.register_CustomerIFSC_Code_id)
        userAccountNumber = findViewById(R.id.register_CustomerAccountNo_id)
        userMobileNo = findViewById(R.id.register_CustomerMobile_id)
        userLivingArea = findViewById(R.id.register_CustomerArea_id)
        userLivingTown = findViewById(R.id.register_CustomerTown_id)
        userLivingCity = findViewById(R.id.register_CustomerCity_id)
        userLivingState = findViewById(R.id.register_CustomerState_id)
        userLivingPinCode = findViewById(R.id.register_CustomerPincode_id)
        userFirstPassword = findViewById(R.id.register_CustomerFirstPassword_id)
        userSecondPassword = findViewById(R.id.register_CustomerSecondPassword_id)
        userMpin = findViewById(R.id.register_CustomerMPin_id)
        checkBox = findViewById(R.id.registerCustomerTC_id)
        registerButton = findViewById(R.id.register_card_button_id)

        val stateAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, indianStates)
        userLivingState?.adapter = stateAdapter
        userSelfie?.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permissions, PersmissionCode)
                } else {
                    openCamera()
                }
            } else {
                openCamera()
            }
        }

        registerButton?.setOnClickListener {

            //userSelfie = Integer.parseInt(image_uri)
            takeCustomerID = userCustomerID?.text.toString()
            takeCustomerFirstName = userFirstName?.text.toString()
            takeCustomerLastName = userLastName?.text.toString()
            takeCustomerEmailID = userEmail?.text.toString()
            takeCustomerIfscCode = userIfscCode?.text.toString()    //
            takeCustomerAccountNo = userAccountNumber?.text.toString()  //
            takeCustomerMobileNo = userMobileNo?.text.toString()
            takeCustomerArea = userLivingArea?.text.toString()
            takeCustomerTown = userLivingTown?.text.toString()
            takeCustomerCity = userLivingCity?.text.toString()
            takeCustomerState = userLivingState?.selectedItem.toString()
            takeCustomerPincode = userLivingPinCode?.text.toString()
            takeCustomerFirstPassword = userFirstPassword?.text.toString()
            takeCustomerSecondPassword = userSecondPassword?.text.toString()
            takeCustomerMPIN = userMpin?.text.toString()


            isTCAccept = checkBox?.isChecked!!

            if(takeCustomerID.toString().isNotEmpty())
            {
                val cursor = helper.getCustomerId()
                var isIDAvailable = false
                if(cursor?.count == 0)
                    Toast.makeText(this,"Database Sounds Empty",Toast.LENGTH_LONG).show()
                else
                {
                    var rowID : Int
                    while(cursor!!.moveToNext())
                    {
                        rowID = cursor.getInt(0)
                        if(takeCustomerID == rowID.toString())
                            userCustomerID?.error = "Customer ID Already Exists"
                    }


                }

            }

            if (TextUtils.isEmpty(takeCustomerID.toString()) ) {
                userCustomerID?.error = "Customer ID Should not be Empty"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerFirstName.toString())) {
                userFirstName?.error = "What is your First Name?"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerLastName.toString())) {
                userLastName?.error = "What is your Last Name?"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerEmailID.toString())) {
                userEmail?.error = "What is your Email ID?"
                return@setOnClickListener
            } else if(TextUtils.isEmpty(takeCustomerIfscCode.toString())){
                if(takeCustomerIfscCode.toString().length < 11 || takeCustomerIfscCode.toString().length > 11)
                {
                    userIfscCode?.error = "Invalid IFSC Code"
                }
                return@setOnClickListener
            }else if(TextUtils.isEmpty(takeCustomerAccountNo.toString())){
                userAccountNumber?.error = "Invalid Account No Entered"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerMobileNo.toString())) {
                userMobileNo?.error = "Please Enter 10 Digit Mobile No"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerArea.toString())) {
                userLivingArea?.error = "Nearby Area Where you Live"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerTown.toString())) {
                userLivingTown?.error = "In Which Town You are Living?"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerCity.toString())) {
                userLivingCity?.error = "In Which City You are Living?"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerPincode.toString())) {
                userLivingPinCode?.error = "Your Area Postal Code"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerFirstPassword.toString())) {
                userFirstPassword?.error = "Without Password you cannot login"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerSecondPassword.toString())) {
                userFirstPassword?.error = "Please Match the Password"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(takeCustomerMPIN.toString())) {
                userMpin?.error = "M-Pin require to send Money"
                return@setOnClickListener
            }

            if (takeCustomerFirstPassword != takeCustomerSecondPassword) {
                userFirstPassword?.error = "Password NOT Match"
                userSecondPassword?.error = "Password NOT Match"
            }

            if (isTCAccept == false) {
                val tcDialog = AlertDialog.Builder(this)
                tcDialog.setTitle("Dialog : 1/2")
                tcDialog.setMessage("Please Accept our Term and Condition")
                tcDialog.setPositiveButton("OK") { dialog, _ ->
                    dialog.cancel()
                }
                tcDialog.show()
            }

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Dialog : 2/2")
            alertDialog.setMessage("Are you Take Selfie?")
            alertDialog.setPositiveButton("Yes I took") { _, _ ->


                helper.insertAllCustomersData(takeCustomerID!!.toInt(), takeCustomerFirstName,takeCustomerLastName, takeCustomerEmailID,takeCustomerIfscCode,takeCustomerAccountNo,
                    takeCustomerMobileNo!!.toLong(),takeCustomerArea,takeCustomerTown,takeCustomerCity,takeCustomerState,
                    takeCustomerPincode!!.toInt(),takeCustomerSecondPassword,takeCustomerMPIN!!.toInt(),isTCAccept)
                finish()
            }
            alertDialog.setNegativeButton("No,Let me take Selfie") { dialog, _ ->
                dialog.cancel()
            }
            alertDialog.show()


//            Toast.makeText(this,"$takeCustomerID \n $takeCustomerFirstName \n $takeCustomerLastName \n" +
//                    "$takeCustomerEmailID \n $takeCustomerMobileNo \n $takeCustomerArea \n $takeCustomerTown \n " +
//                    "$takeCustomerCity \n $takeCustomerState \n $takeCustomerPincode \n $takeCustomerFirstPassword \n" +
//                    "$takeCustomerSecondPassword \n $takeCustomerMPIN ",Toast.LENGTH_LONG).show()
//            // image_uri = content://media/external/images/media70611

        }

    }


    private fun openCamera() {
        val cv = ContentValues()
        cv.put(MediaStore.Images.Media.TITLE, "New Pic")
        cv.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(intent, ImageCapturecode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PersmissionCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    openCamera()
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            userSelfie?.setImageURI(image_uri)
            img_uri =image_uri.toString().toByteArray()

            //var img_uri2 = data?.data
            //var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,img_uri2)

            //val Bstream = ByteArrayOutputStream()
            //val photo = intent.extras!!.get("data") as Bitmap
            //photo.compress(Bitmap.CompressFormat.PNG, 100, Bstream)
            //byteArray = Bstream.toByteArray()


        }

    }
}


/*
* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
                val Bstream = ByteArrayOutputStream()
                val photo = intent.extras!!.get("data") as Bitmap
                photo.compress(Bitmap.CompressFormat.PNG, 100, Bstream)
                byteArray = Bstream.toByteArray()
                imgStatus = helper.insertImage_ID(byteArray!!, takeCustomerID?.toInt())

                if (imgStatus == -1.toLong())
                    Toast.makeText(this, "error to insert img", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, "image insert", Toast.LENGTH_LONG).show()

            userSelfie?.setImageURI(image_uri)
        }

    }
*
*
* */

