package com.example.usersdatainlist

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    private var listView : ListView? = null
    private var dataList : MutableList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.id_listView)

        getCustomData()
        listView?.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)

        listView?.setOnItemClickListener { parent, view, position, id ->

            val fname = parent.getItemAtPosition(position).toString()
            val userData = fname.split(" \n ")
            //Log.e("AXE",userData[0])
            //Log.e("AXE","Last Name : ${userData[1]}")
            //Log.e("AXE","Email : ${userData[2]}")
            //Log.e("AXE","Mobile : ${userData[3]}")

            AlertDialog.Builder(this)
                .setTitle("User Data")
                .setMessage(" ${userData[0]} \n ${userData[1]} \n ${userData[2]} \n ${userData[3]}")
                .setPositiveButton("OK"){d,_ ->
                    d.dismiss()
                }.show()
        }





    }

    private fun getCustomData() {

        val resolver =  contentResolver
        val uri = Uri.parse("content://com.example.UserData_PROVIDER")

        val cursor = resolver.query(uri,null,null,null,null)

        while(cursor!!.moveToNext()){
            val Fname = cursor.getString(cursor.getColumnIndex("FirstName"))
            val Lname = cursor.getString(cursor.getColumnIndex("LastName"))
            val Email = cursor.getString(cursor.getColumnIndex("EmailID"))
            val Mobile = cursor.getLong(cursor.getColumnIndex("MobileNo"))

            dataList.add("First Name : $Fname \n Last Name : $Lname \n Email ID : $Email \n Mobile No : $Mobile")

        }


    }
}
