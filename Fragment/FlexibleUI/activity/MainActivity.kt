package com.example.flexible_ui_interface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() , Communicator {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun sendData(string: String) {
        val frag2 = supportFragmentManager
        val f2 = frag2.findFragmentById(R.id.fragment_2_id) as? Fragment_2
        if(f2 != null && f2.isVisible){
            f2.recivedData(string)
        }
        else
        {
            var intent = Intent(this,DisplayData::class.java)
            intent.putExtra("description",string)
            startActivity(intent)
        }
    }
}
