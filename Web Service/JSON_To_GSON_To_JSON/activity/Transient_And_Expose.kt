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
