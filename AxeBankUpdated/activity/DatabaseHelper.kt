package com.example.axebank

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.CursorIndexOutOfBoundsException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import java.io.ByteArrayInputStream


private var DATABASE_NAME = "BANK_CUSTOMER"

private var USER_SELFIE_TABLE = "USER_SELFIE_TABLE"
private var SELFIE_COL_1 = "CustomerID"
private var SELFIE_COL_2 = "SelfieImage"

private var CUSTOMER_DETAIL_TABLE = "CUSTOMER_DETAILS"
private var COL_1 = "ID"
private var COL_2 = "FirstName"
private var COL_3 = "LastName"
private var COL_4 = "Email"
private var COL_5 = "IFSC_Code"
private var COL_6 = "Account_No"
private var COL_7 = "Mobile"
private var COL_8 = "Area"
private var COL_9 = "Town"
private var COL_10 = "City"
private var COL_11 = "State"
private var COL_12 = "Pincode"
private var COL_13= "Password"
private var COL_14 = "M_Pin"
private var COL_15 = "isTC_Accept"

private var SEND_MONEY_TABLE = "SEND_MONEY_HISTORY"
private var SM_COL_1 = "Customer_ID"
private var SM_COL_2 = "Bank_Name"
private var SM_COL_3 = "IFSC_Code"
private var SM_COL_4 = "Account_No"
private var SM_COL_5 = "Amount"
private var SM_COL_6 = "Message"
private var SM_COL_7 = "DateTime"

private var BANK_BALENCE_TABLE = "BankBalance"
private var BB_COL_1 = "Customer_ID"
private var BB_COL_2 = "Balance"





class DatabaseHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,5)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create Table $USER_SELFIE_TABLE($SELFIE_COL_1 INTEGER, $SELFIE_COL_2 BLOB)")
        db?.execSQL("Create table $CUSTOMER_DETAIL_TABLE( $COL_1 INTEGER PRIMARY KEY, $COL_2 TEXT, $COL_3 TEXT, $COL_4 TEXT, $COL_5 TEXT, $COL_6 TEXT, $COL_7 TEXT, $COL_8 TEXT, $COL_9 TEXT, $COL_10 TEXT, $COL_11 TEXT, $COL_12 INTEGER,$COL_13 TEXT,$COL_14 INTEGER,$COL_15 BOOLEAN)")
        db?.execSQL("Create Table $BANK_BALENCE_TABLE($BB_COL_1 INTEGER , $BB_COL_2 INTEGER)")
        db?.execSQL("Create Table $SEND_MONEY_TABLE ($SM_COL_1 TEXT,$SM_COL_2 TEXT,$SM_COL_3 TEXT,$SM_COL_4 TEXT,$SM_COL_5 TEXT,$SM_COL_6 TEXT, $SM_COL_7 TEXT ) ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_SELFIE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CUSTOMER_DETAIL_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $BANK_BALENCE_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $SEND_MONEY_TABLE")
    }

    fun insertImage_ID(customer_id : Int?,photo: ByteArray?): Long {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("CustomerID",customer_id)
        cv.put("SelfieImage",photo)
        return db.insert(USER_SELFIE_TABLE, null, cv)
    }

    fun getImageFromCustomerID(cid: Int): Bitmap? {
        val db = writableDatabase
        var bitmap : Bitmap? = null
        //var photo : ByteArray? = null
        val res = db.rawQuery("SELECT $SELFIE_COL_2 from $USER_SELFIE_TABLE where $SELFIE_COL_1 = $cid ;",null)


        while (res.moveToNext())
            bitmap = BitmapFactory.decodeByteArray(res.getBlob(res.getColumnIndex("SelfieImage")),0,res.getBlob(res.getColumnIndex("SelfieImage")).size)

        //while(res.moveToNext())
        //{
         //   photo = res.getBlob(res.getColumnIndex("SelfieImage"))
           // bitmap = BitmapFactory.decodeByteArray(photo,0,photo.size)
       // }
        //Toast.makeText(context,"$bitmap",Toast.LENGTH_LONG).show()
        return bitmap

    }

    fun insertAllCustomersData(c_id : Int,firstName : String?,lastName : String?, emailID : String?,ifscCode : String?,accountNo : String?, mobileNo : Long,area : String?, town : String?, city : String?,state : String?,
                               pincode : Int, password : String?, m_pin : Int?,isTCAccept : Boolean)
    {

        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_1,c_id)
        cv.put(COL_2,firstName)
        cv.put(COL_3,lastName)
        cv.put(COL_4,emailID)
        cv.put(COL_5,ifscCode)
        cv.put(COL_6,accountNo)
        cv.put(COL_7,mobileNo)
        cv.put(COL_8,area)
        cv.put(COL_9,town)
        cv.put(COL_10,city)
        cv.put(COL_11,state)
        cv.put(COL_12,pincode)
        cv.put(COL_13,password)
        cv.put(COL_14,m_pin)
        cv.put(COL_15,isTCAccept)

        val result = db.insert(CUSTOMER_DETAIL_TABLE,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success", Toast.LENGTH_LONG).show()

    }

    fun sendMoneyHistory(cid : String?,bankName : String?, ifsc_code : String?, accountNumber : String?,userAmount : String?,message : String?,dateTime : String?) : Boolean
    {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(SM_COL_1,cid)
        cv.put(SM_COL_2,bankName)
        cv.put(SM_COL_3,ifsc_code)
        cv.put(SM_COL_4,accountNumber)
        cv.put(SM_COL_5,userAmount)
        cv.put(SM_COL_6,message)
        cv.put(SM_COL_7,dateTime)

        val result = db.insert(SEND_MONEY_TABLE,null,cv)
        return if(result == -1.toLong()) false else true

    }

    fun getCustomerIDPassword() : Cursor {
        val db = readableDatabase
        return  db.rawQuery("select $COL_1 , $COL_13 from $CUSTOMER_DETAIL_TABLE;",null)



    }

//    fun getBankBalence(cid : Int) : Cursor
//    {
//        val db = readableDatabase
//        return db.rawQuery("select $BB_COL_2 from $BANK_BALENCE_TABLE where $BB_COL_1 = $cid",null)
//    }



    fun getMpin() : Cursor
    {
        val db = readableDatabase
        return db.rawQuery("select $COL_1 , $COL_14 from $CUSTOMER_DETAIL_TABLE ",null)
    }
    fun getFullName (cid : Int) : Cursor
    {
        val db = readableDatabase
       return db.rawQuery("select $COL_1 , $COL_2 , $COL_3 from $CUSTOMER_DETAIL_TABLE ",null)


        /*
        val db = readableDatabase
        var fname : String? = "Fname"
        var lname : String? = "lname"
        val res = db.rawQuery("select $COL_2 , $COL_3 from $CUSTOMER_DETAIL_TABLE where $COL_1 = $cid",null)
        Toast.makeText(context,"${res.getString(-1)}",Toast.LENGTH_LONG).show()
        if(res != null && res.moveToFirst())
        {
            do {
                fname = res.getString(0)                // ok
                lname = res.getString(1)
            }while(res.moveToNext())
        }
        Toast.makeText(context,"$fname : $lname",Toast.LENGTH_LONG).show()
        return "$fname $lname"
*/



//        if(res.count ==0)
//            Toast.makeText(context,"cannot fetch data",Toast.LENGTH_LONG).show()
//        if(res.moveToNext()) {
//            fname = res.getString(0)
//
//            lname = res.getString(1)
//        }
//        return "$fname $lname$"
    }

    fun getCustomerId() : Cursor?
    {
        val db = readableDatabase
        return db?.rawQuery("select $COL_1 from $CUSTOMER_DETAIL_TABLE",null)
    }

    fun getPassword() : Cursor
    {
        val db = readableDatabase
        return db.rawQuery("select $COL_1,$COL_13 from $CUSTOMER_DETAIL_TABLE",null)
    }

    fun getHistory(cid : Int) : Cursor?
    {
        val db = readableDatabase
        return db?.rawQuery("select * from $SEND_MONEY_TABLE where $SM_COL_1 = $cid",null)
    }

    fun getAllData(cid : Int) : Cursor?
    {
        val db = readableDatabase
        return db?.rawQuery("select * from $CUSTOMER_DETAIL_TABLE where $COL_1 = $cid",null)
    }
    fun getMpin(cid : Int) : Cursor
    {
        val db = readableDatabase
        return db.rawQuery("select $COL_1 , $COL_14 from $CUSTOMER_DETAIL_TABLE ",null)
    }

    fun deleteAccount(cid : Int) : Int
    {
        val db = writableDatabase
        return db.delete(CUSTOMER_DETAIL_TABLE,"$COL_1 = $cid",null)

    }

    fun updateCustomerData(customer_id: Int?,
                           FirstName : String?,
                           LastName : String?,
                           Email : String?,
                           IfscCode : String?,
                           AccountNumber : String?,
                           MobileNo : String?,
                           LivingArea : String?,
                           LivingTown : String?,
                           LivingCity : String?,
                           LivingState : String?,
                           LivingPinCode : String?,
                           SecondPassword : String?,
                           Mpin : Int?) : Boolean
    {
        val db = readableDatabase
        val cv = ContentValues()

        cv.put(COL_2,FirstName)
        cv.put(COL_3,LastName)
        cv.put(COL_4,Email)
        cv.put(COL_5,IfscCode)
        cv.put(COL_6,AccountNumber)
        cv.put(COL_7,MobileNo)
        cv.put(COL_8,LivingArea)
        cv.put(COL_9,LivingTown)
        cv.put(COL_10,LivingCity)
        cv.put(COL_11,LivingState)
        cv.put(COL_12,LivingPinCode)
        cv.put(COL_13,SecondPassword)
        cv.put(COL_14,Mpin)

        val x = db.update(CUSTOMER_DETAIL_TABLE,cv,"$COL_1 = ?", arrayOf(customer_id.toString()))
        return  x != -1


//        return db.rawQuery("update $CUSTOMER_DETAIL_TABLE set $COL_2 = $FirstName, $COL_3 = $LastName,$COL_4 = $Email," +
//        "$COL_5 = $IfscCode,$COL_6 = $AccountNumber, $COL_7 = $MobileNo, $COL_8 = $LivingArea,$COL_9 = $LivingTown," +
//        "$COL_10 = $LivingCity, $COL_11 = $LivingState,$COL_12 = $LivingPinCode, $COL_13 = $SecondPassword," +
//        "$COL_14 = $Mpin where $COL_1 = $customer_id",null)
    }




}