package com.example.listfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class MainActivity : AppCompatActivity(), Communicator {

    var frag1 = Fragment_A()
    var frag2 = Fragment_B()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Frm1stFrag2Second"
    }

    override fun respond(username: String, password: String) {

        val fm = supportFragmentManager
        val f2 = fm.findFragmentById(R.id.fragment2_id) as Fragment_B
        f2.collectData(username,password)




    }

}
