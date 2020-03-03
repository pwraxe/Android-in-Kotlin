package com.example.from1fragment2secondfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
class MainActivity : AppCompatActivity() {

    private val design = Design()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fm = supportFragmentManager
        var transaction = fm.beginTransaction()
        transaction.add(R.id.my_layout,design,"DESIGN")
        transaction.commit()


    }
}
