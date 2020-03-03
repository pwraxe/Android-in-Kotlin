package com.example.fragment_part_insingleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), Communicater {

    var s : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Axe MainActivity","on_Create")
    }

    override fun respond(s: String) {
        var fm = supportFragmentManager
        var f2 = fm.findFragmentById(R.id.fragment_2_id) as SecondFragment
        f2.getData(s)

    }

    override fun onStart() {
        super.onStart()
        Log.e("Axe MainActivity","on_Start")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Axe MainActivity","on_Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Axe MainActivity","on_Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Axe MainActivity","on_Stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Axe MainActivity","on_Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Axe MainActivity","on_Destroy")
    }

}
