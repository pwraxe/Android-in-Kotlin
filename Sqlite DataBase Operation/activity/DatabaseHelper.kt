package com.example.databaseoperation

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.math.BigInteger


private var DATABASE_NAME = "Human"
private var TABLE_NAME = "Student"
private var COL_1 = "ID"
private var COL_2 = "First_Name"
private var COL_3 = "Last_Name"
private var COL_4 = "Email"
private var COL_5 = "MobileNo"
private var COL_6 = "Qualification"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,2) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABLE_NAME ( $COL_1 INTEGER primary key not null,$COL_2 varchar(20)," +
                " $COL_3 varchar(20),$COL_4 varchar(50),$COL_5 Long,$COL_6 varchar(30));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table $TABLE_NAME if exists")
    }

    fun insertData(id : Int,fname : String, lname : String,email : String, mobile : Long,qualification : String ) : Boolean {
        val isInsert : Boolean
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_1,id)
        cv.put(COL_2,fname)
        cv.put(COL_3,lname)
        cv.put(COL_4,email)
        cv.put(COL_5,mobile)
        cv.put(COL_6,qualification)

        val r = db.insert(TABLE_NAME,null,cv)
        isInsert = r != -1.toLong()
        return isInsert


    }


    fun selectAllData() : Cursor? {
        val db = readableDatabase
        return db.rawQuery("select * from $TABLE_NAME; ",null)

    }

    fun getDataFromID(id : Int) : Cursor? {

        val db = readableDatabase
        return db.rawQuery("select * from $TABLE_NAME where $COL_1 = $id",null)

    }

    fun updateData(id : Int,fname : String,lname : String,email : String,mobile : Long,quali : String) : Boolean {

        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COL_2,fname)
        cv.put(COL_3,lname)
        cv.put(COL_4,email)
        cv.put(COL_5,mobile)
        cv.put(COL_6,quali)
        val u = db.update(TABLE_NAME,cv,"$COL_1 = ?", arrayOf(id.toString()))
        return u != -1

    }

    fun getUserID() : Cursor  {

        val db = readableDatabase
        return db.rawQuery("select $COL_1 from $TABLE_NAME",null)
    }

    fun deleteData(id : Int) : Int
    {
        val db = writableDatabase
        return db.delete(TABLE_NAME,"$COL_1 = ?", arrayOf("$id"))
    }



}
