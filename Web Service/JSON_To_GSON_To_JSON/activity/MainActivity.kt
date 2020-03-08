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
        // retain it back to java object
        val jsonform = "{\"address\": {\"city\":\"Nashik\",\"country\":\"India\",\"pin\":422103,\"state\":\"Maharashtra\",\"town\":\"Sinnar\"},\"age\":23,\"email\":\"pawarakshay13@gmail.com\",\"name\":\"Akshay Pawar\"}"
        val gsonObj = Gson()
        val emp : Employee = gsonObj.fromJson(jsonform,Employee::class.java)

        Log.e("AXE", emp.name)
        Log.e("AXE", emp.email)
        Log.e("AXE","${emp.age}")
        Log.e("AXE","${emp.address.country} > ${emp.address.state} > ${emp.address.city} > ${emp.address.town} > ${emp.address.pin}")
    /*

    ---output---

        E/AXE: Akshay Pawar
        E/AXE: pawarakshay13@gmail.com
        E/AXE: 23
        E/AXE: India > Maharashtra > Nashik > Sinnar > 422103

    */

    }
}

data class Employee(var name : String,var email : String,var age : Int, var address : Address)
data class Address(var country : String, var state : String, var city : String, var town : String, var pin : Int )
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


// JSON Array

class MainActivity : AppCompatActivity() {

    private var gson: Gson? = null
    val lang = arrayOf("C","C++","Java","Kotlin","PHP","Python","JSP","ASP.NET","JavaScript")
    val emp = ArrayList<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gson = Gson()

        emp.add(Employee("Akshay",23))
        emp.add(Employee("Vishal",23))
        emp.add(Employee("Mayur",23))
        emp.add(Employee("Harsh",24))


        // val jsonData = gson?.toJson(lang)    // ---> OUTPUT---------->  ["C","C++","Java","Kotlin","PHP","Python","JSP","ASP.NET","JavaScript"]

        val jsonData = gson?.toJson(emp)

        /* --------------OUTPUT-----------------
         [
            {"age":23,"name":"Akshay"},
            {"age":23,"name":"Vishal"},
            {"age":23,"name":"Mayur"},
            {"age":24,"name":"Harsh"}
         ]
    */

        Log.e("AXE","$jsonData")
    }
}

class Employee (var name : String, var age : Int)

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


class MainActivity : AppCompatActivity() {

    private var gson : Gson? = null

    var stud = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gson = Gson()
        
        stud.add(Student("Akshay",1001,"SVIT CHINCHOLI"))
        stud.add(Student("Vijay",2002,"KK Wagh"))
        stud.add(Student("Kalpesh",3003,"Sandeep Foundation"))

        val c1 = Collage(1000,250)
        val comb = Combine(stud,c1)
        val toJson = gson?.toJson(comb)
        Log.e("AXE","$toJson")
    }
}

class Student(var name : String,var id : Int,var clg : String)

class Collage(var totalStud : Int,var totalStaff : Int)

class Combine(var stud : ArrayList<Student>,var collage : Collage)

/*
    -----------------------OUTPUT---------------------------
    {
        "collage":
            {"totalStaff":250,"totalStud":1000},
        "stud":
            [
                {
                    "clg":"SVIT CHINCHOLI",
                    "id":1001,"name":"Akshay"
                },
                {
                    "clg":"KK Wagh",
                    "id":2002,
                    "name":"Vijay"
                },
                {
                    "clg":"Sandeep Foundation",
                    "id":3003,
                    "name":"Kalpesh"
                 }
              ]
        }

*/
----------------------------------------------------------------------------------------------------------------------------------------------------------------


class MainActivity : AppCompatActivity() {


    // sending 2 objects for json
    private var gson : Gson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gson = Gson()

        val emp1 = Employee("Akshay Pawar",30000)
        val emp2 = Employee("Sachine Pangarkar",30000)
        val emp3 = Employee("Vishal More",30000)
        var eobj = Employee2Obj(emp1,emp2)
        val jsonData = gson?.toJson(eobj)
        Log.e("AXE","$jsonData")

        /*
        *       // Passing 2 object
        * {
        *   "emp1":
        *       {
        *           "name":"Akshay Pawar",
        *           "sal":30000
        *       },
        *   "emp2":
        *       {
        *           "name":"Sachine Pangarkar",
        *           "sal":30000
        *       }
        * }
        * */

        Log.e("AXE","-----------------------------------------------------")

        val e3obj = Employee3Obj(emp1,emp2,emp3)
        var json3Data = gson?.toJson(e3obj)
        Log.e("AXE","$json3Data")
        /*
        *       // passing 3 object
        * {
        *   "emp1":
        *       {
        *           "name":"Akshay Pawar",
        *           "sal":30000
        *       },
        *   "emp2":
        *       {
        *           "name":"Sachine Pangarkar",
        *           "sal":30000
        *       },
        *       "emp3":
        *       {
        *           "name":"Vishal More",
        *           "sal":30000
        *       }
        * }
        * */
    }
}

class Employee(var name : String, var sal : Int)

class Employee2Obj(var emp1 : Employee,var emp2 : Employee)
class Employee3Obj(var emp1 : Employee,var emp2 : Employee,var emp3: Employee)
