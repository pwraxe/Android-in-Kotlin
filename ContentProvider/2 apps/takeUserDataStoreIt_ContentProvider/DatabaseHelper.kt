package com.example.customcontentprovider

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


private const val DATABASE = "MyLocalDB"
private const val TABLE = "UserInfo"
private const val COL_FN = "FirstName"
private const val COL_LN = "LastName"
private const val COL_EMAIL = "EmailID"
private const val COL_MOBILE = "MobileNo"

class DatabaseHelper(var context: Context?, name: String?,factory: SQLiteDatabase.CursorFactory?,version: Int) : SQLiteOpenHelper(context, name, factory, version)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABLE ($COL_FN TEXT,$COL_LN TEXT, $COL_EMAIL TEXT,$COL_MOBILE LONG);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE if exists $TABLE")
    }

    fun insertData(fname : String,lname : String, email : String,mobile : Long) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_FN,fname)
        cv.put(COL_LN,lname)
        cv.put(COL_EMAIL,email)
        cv.put(COL_MOBILE,mobile)


        val r = db.insert(TABLE,null,cv)
        if(r == -1.toLong()){
            Toast.makeText(context,"Data Not Insert",Toast.LENGTH_LONG).show()
        }else
            Toast.makeText(context,"Data Inserted",Toast.LENGTH_LONG).show()

    }


}