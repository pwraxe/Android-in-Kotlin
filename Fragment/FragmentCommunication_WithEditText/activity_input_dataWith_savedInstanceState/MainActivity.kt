package com.example.fragmentpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(),Communicater {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun shareData(fname: String?,lname : String?) {
        var fm = supportFragmentManager
        var frag2 = fm.findFragmentById(R.id.fragment2_id) as Fragment_2
        frag2.collectData(fname,lname)
    }



}
