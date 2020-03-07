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
