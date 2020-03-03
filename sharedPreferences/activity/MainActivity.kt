package com.example.sharedpreferencesdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var inputBox : EditText? = null
    private var saveButton : Button? = null
    private var userText : TextView? = null
    private var loadSaveDataButton : Button? = null
    private var countText : TextView? = null


    val PREF_NAME = "MY_PREF"
    val USER_SAVED_TEXT = "Akshay"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText = findViewById(R.id.id_textView)
        inputBox = findViewById(R.id.id_textBox)
        saveButton = findViewById(R.id.id_button)
        userText = findViewById(R.id.id_userText)
        loadSaveDataButton = findViewById(R.id.id_loadSaveDataButton)



        val ownPref = OwnPreferences(this)
        var count = ownPref.getCount()
        count++

        ownPref.setCount(count)
        countText?.text = "App open at $count times"


        saveButton?.setOnClickListener {

            userText?.text = "${inputBox?.text?.toString()}"
            val pref = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

            val change = pref.edit()
            change.putString("Upload","${inputBox?.text?.toString()}")
            change.apply()

        }

        loadSaveDataButton?.setOnClickListener {

            val pref = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            val data = pref.getString("Upload",USER_SAVED_TEXT)
            userText?.text = data.toString()

        }
    }
}
