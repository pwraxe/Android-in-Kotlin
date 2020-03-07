package com.example.json_to_gson_to_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // From Java Object to JSON
//          val gson = Gson()
//          val emp = Employee("Akshay Pawar",23,"pawarakshay13@gmail.com")
//          val data = gson.toJson(emp)
//          Log.e("AXE",data)

        // From JSON to java Object
        val gson = Gson()
        val json = "{\"age\":23,\"email\":\"pawarakshay13@gmail.com\",\"name\":\"Akshay Pawar\"}"
        val emp : Employee = gson.fromJson(json,Employee::class.java)
        Log.e("AXE","${emp.name} \n " +             // Akshay Pawar
                " ${emp.age} \n" +                          // 23
                " ${emp.email}")                            // pawarakshay13@gmail.com

    }
}


class Employee(var name : String,var age : Int, var email : String)
----------------------------------------------------------------------------------------------------------------------------------------------------------
other file : 
-------------
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val address : Address = Address("India","Maharashtra","Nashik","Sinnar",422103)
        val employee : Employee = Employee("Akshay Pawar","pawarakshay13@gmail.com",23,address)

        val gson = Gson()
        val jsonData = gson.toJson(employee)
        Log.e("AXE",jsonData)

/*
        {
            "address":
            {
                "city":"Nashik",
                "country":"India",
                "pin":422103,
                "state":"Maharashtra",
                "town":"Sinnar"
            },
            "age":23,
            "email":"pawarakshay13@gmail.com",
            "name":"Akshay Pawar"
        }
*/
    }
}

data class Employee(var name : String,var email : String,var age : Int, var address : Address)
data class Address(var country : String, var state : String, var city : String, var town : String, var pin : Int )
