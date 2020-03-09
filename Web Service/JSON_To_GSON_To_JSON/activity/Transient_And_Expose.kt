import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        val stud = Student("Akshay","pawarakshay13@gmail.com",1001)
        var jsonData = gson.toJson(stud)
        Log.e("AXE","$jsonData")

    }
}
class Student (@Transient private var fname : String, @Transient private var email : String,var id : Int)

// due to 'transient' keyword data is not serialized and id is not serialized hence we got output as -->  {"id":1001}
//________________________________________________________________________________________________________________________________________________________________________

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.Expose

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        val stud = Student("Alex","alexandy@gmail.com",1001,"Amity University",2015,"B.E.I.T.")
        var jsonData = gson.toJson(stud)
        Log.e("AXE","$jsonData")
    }
}
class Student (@Transient private var fname : String, @Expose private var email : String,
               @Expose(serialize = true) var id : Int, @Expose(serialize = false) private var collage : String,
               @Expose(deserialize = true) private var passYear : Int, @Expose(deserialize = false) private var quali : String)


/*
* ----------OUTPUT-----------
*
* {
*   "collage":"Amity University",       // @Expose(serialize = false)
*   "email":"alexandy@gmail.com",       // @Expose
*   "id":1001,                          // @Expose(serialize = true)
*   "passYear":2015,                    // @Expose(deserialize = true)
*   "quali":"B.E.I.T."                  // @Expose(deserialize = false)
* }
*
* */

____________________________________________________________________________________________________________________________________________________________________________________

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val stud = Student("Alex","alexandy@gmail.com",1001,"Amity University",2015,"B.E.I.T.")
        var jsonData = gson.toJson(stud)
        Log.e("AXE","$jsonData")
    }
}
class Student (@Transient private var fname : String,                   // variable ignore
               @Expose private var email : String,                      // can be serialize and deserialize
               @Expose(serialize = true) var id : Int,                  // can serialized
               @Expose(serialize = false) private var collage : String, // cannot serialized
               @Expose(deserialize = true) private var passYear : Int,  // can be deserialize i.e. can convert from json to Object
               @Expose(deserialize = false) private var quali : String) // cannot be deserialize i.e. cannot convert from json to Object


/*
* ----------OUTPUT-----------
*
* {
*   "email":"alexandy@gmail.com",           // @Expose
*   "id":1001,                              // @Expose(serialize = true)
*   "passYear":2015,                        // @Expose(deserialize = true)
*   "quali":"B.E.I.T."                      // @Expose(deserialize = false)
* }
* */


_____________________________________________________________________________________________________________________________________________________________________________


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val stud = Student("Alex","alexandy@gmail.com",1001,"Amity University",2015,"B.E.I.T.")
        var jsonData = gson.toJson(stud)
        Log.e("AXE","$jsonData")

        var s = gson.fromJson(jsonData,Student::class.java)
        Log.e("AXE","\n ID : ${s.id} \n Email  : ${s.email} \n Collage : ${s.collage} \n PassYear : ${s.passYear} \n Quali : ${s.quali} ")
    }
}
class Student (@Transient private var fname : String,
               @Expose var email : String,
               @Expose(serialize = false) var id : Int,
               @Expose(serialize = false) var collage : String,

               @Expose(deserialize = false) var passYear : Int,
               @Expose(deserialize = false) var quali : String)


/*
* _____________________________________________________________________________________________________________________________________________________________________
*
* Case : 1
*
* -----------Serialized Object------------
*       {
*           "email":"alexandy@gmail.com",
*           "passYear":2015,
*           "quali":"B.E.I.T."
*       }
*-----------Deserialize Object ----------------
*       ID : 0                          // @Expose(serialize = false)
*       Email  : alexandy@gmail.com
*       Collage : null                  // @Expose(serialize = false)
*       PassYear : 0                    // @Expose(deserialize = false)
*       Quali : null                    // @Expose(deserialize = false)
* */



//=========================================================================================================================================

/*
Case : 2

class Student (@Transient private var fname : String,
               @Expose var email : String,
               @Expose(serialize = true) var id : Int,
               @Expose(serialize = false) var collage : String,
               @Expose(deserialize = true) var passYear : Int,
               @Expose(deserialize = false) var quali : String)

* --------------- serialised -------------------
* {
*   "email":"alexandy@gmail.com",
*   "id":1001,
*   "passYear":2015,                // we specify condition for only deserialize hence it by default serialized
*   "quali":"B.E.I.T."              // we specify condition for only deserialize hence it by default serialized
* }
*
* ---------- Deserialize ---------------
*
*   ID : 1001                       // @Expose(serialize = true)
*   Email  : alexandy@gmail.com     // @Expose  i.e. serialised and Deserialize is true by default
*   Collage : null                  // @Expose(serialize = false)
*   PassYear : 2015                 // @Expose(deserialize = true)
*   Quali : null                    // @Expose(deserialize = false) don't do deserialize i.e. don't convert object again
________________________________________________________________________________________________________________________________________________

Case : 3

* class Student (@Transient private var fname : String,
               @Expose var email : String,
               @Expose(serialize = false) var id : Int,
               @Expose(serialize = false) var collage : String,

               @Expose(deserialize = true) var passYear : Int,
               @Expose(deserialize = true) var quali : String)

--------------------------------------- OUTPUT ---------------------------------------------------------------

--------- Serialised Data (Base on Condition) ----------------

        {
           "email":"alexandy@gmail.com",
           "passYear":2015,
           "quali":"B.E.I.T."
        }

---------Deserialize Data (Base on Condition)----------------
    ID : 0                          //  @Expose(serialize = false)
    Email  : alexandy@gmail.com     //  @Expose i.e no connditon --> can be serialized and Deserialize
    Collage : null                  //  @Expose(serialize = false)
    PassYear : 2015                 //  @Expose(deserialize = true)  --> can be deserialize
    Quali : B.E.I.T.                //  @Expose(deserialize = true)  --> can be deserialize
*/
