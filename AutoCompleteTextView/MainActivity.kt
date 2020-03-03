package com.example.autocompletetextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var daysHint = arrayOf("sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
    private var autoCompleteBox : AutoCompleteTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteBox = findViewById(R.id.autoCompTextView_id)
        autoCompleteBox?.threshold = 0
        val autoAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,daysHint)
        autoCompleteBox?.setAdapter(autoAdapter)

        autoCompleteBox?.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)
                autoCompleteBox?.showDropDown()
        }
        autoCompleteBox?.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this,"${parent.getItemAtPosition(position)}",Toast.LENGTH_LONG).show()
        }


    }
}
