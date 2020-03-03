package com.example.checkboxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var cppLang : CheckBox? = null
    private var javaLang : CheckBox? = null
    private var kotlinLang : CheckBox? = null
    private var androidLang : CheckBox? = null
    private var iosLang : CheckBox? = null
    private var submitButton : Button? = null

    private var cppText = ""
    private var javaText = ""
    private var kotlinText = ""
    private var androidText = ""
    private var iosText = ""

    private var selectedLanguage : MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cppLang = findViewById(R.id.cpp_id)
        javaLang = findViewById(R.id.java_id)
        kotlinLang = findViewById(R.id.kotlin_id)
        androidLang = findViewById(R.id.android_id)
        iosLang = findViewById(R.id.ios_id)

        submitButton = findViewById(R.id.button_id)
        submitButton?.setOnClickListener {
            selectedLanguage = mutableListOf()
            cppText = if(cppLang!!.isChecked) selectedLanguage?.add(cppLang?.text.toString()).toString() else null.toString()
            javaText = if(javaLang!!.isChecked) selectedLanguage?.add(javaLang?.text.toString()).toString()  else null.toString()
            kotlinText = if(kotlinLang!!.isChecked) selectedLanguage?.add(kotlinLang?.text.toString()).toString() else null.toString()
            androidText = if(androidLang!!.isChecked) selectedLanguage?.add( androidLang?.text.toString()).toString() else null.toString()
            iosText = if(iosLang!!.isChecked) selectedLanguage?.add(iosLang?.text.toString()).toString() else null.toString()

            Toast.makeText(this,"$cppText : $javaText : $kotlinText : $androidText : $iosText : $selectedLanguage",Toast.LENGTH_LONG).show()



        }




    }
}
