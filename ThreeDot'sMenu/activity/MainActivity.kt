package com.example.threedotmenu

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar = findViewById<View>(R.id.toolbar_id) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolBar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.android_id -> showToast(item.title.toString())
            R.id.cake_id -> showToast(item.title.toString())
            R.id.cloud_id -> showToast(item.title.toString())
            R.id.education_id -> showToast(item.title.toString())
            R.id.flight_id -> showToast(item.title.toString())
            R.id.laptop_id -> showToast(item.title.toString())
            R.id.mobile_id -> showToast(item.title.toString())
            R.id.moon_id -> showToast(item.title.toString())
        }


        return super.onOptionsItemSelected(item)
    }

    private fun showToast(s : String?) = Toast.makeText(this,s,Toast.LENGTH_LONG).show()
}
