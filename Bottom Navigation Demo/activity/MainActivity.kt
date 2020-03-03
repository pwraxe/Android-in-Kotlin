package com.example.bottomnavigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var navigation : BottomNavigationView? = null
    private var pickText : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation = findViewById(R.id.bottom_navi_id)
        pickText = findViewById(R.id.row_text_id)



        navigation?.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_id -> {
                    Toast.makeText(this,it.title.toString(),Toast.LENGTH_LONG).show()
                    pickText?.text = it.title.toString()
                }
                R.id.shop_id -> {
                    Toast.makeText(this,it.title.toString(),Toast.LENGTH_LONG).show()
                    pickText?.text = it.title.toString()
                }
                R.id.setting_id -> {
                    Toast.makeText(this,it.title.toString(),Toast.LENGTH_LONG).show()
                    pickText?.text = it.title.toString()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
}
