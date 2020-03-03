package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity2 : AppCompatActivity() {


    private var numbers : TextView? = null
    private var addButton : Button? = null
    private var subButton  : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val intent = intent
        val num1 = intent.getIntExtra("NUM1",3)
        val num2 = intent.getIntExtra("NUM2",5)

        numbers = findViewById(R.id.id_numbers)
        addButton = findViewById(R.id.id_add_button)
        subButton = findViewById(R.id.id_sub_button)

        numbers?.text = "Numbers : $num1, $num2"

        addButton?.setOnClickListener {

            val result = num1 + num2
            val intent = Intent()
            intent.putExtra("RESULT",result)
            setResult(Activity.RESULT_OK,intent)
            finish()

        }

        subButton?.setOnClickListener {

            val result = num1 - num2
            val intent = Intent()
            intent.putExtra("RESULT",result)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }



    }
}
