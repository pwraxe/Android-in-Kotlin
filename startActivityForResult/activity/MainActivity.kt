package com.example.onactivityresult

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var firstNum : EditText? = null
    private var secondNum : EditText? = null
    private var act2Button : Button? = null
    private var result : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNum = findViewById(R.id.id_num1)
        secondNum = findViewById(R.id.id_num2)
        act2Button = findViewById(R.id.id_activity2Button)
        result = findViewById(R.id.id_result_text)

        act2Button?.setOnClickListener {
            if(firstNum?.text.toString().isEmpty() || secondNum?.text.toString().isEmpty())
                Toast.makeText(this,"fields Cannot be empty",Toast.LENGTH_LONG).show()
            else {
                val num1 = firstNum?.text.toString().toInt()
                val num2 = secondNum?.text.toString().toInt()
                val intent = Intent(this,Activity2::class.java)
                intent.putExtra("NUM1",num1)
                intent.putExtra("NUM2",num2)
                startActivityForResult(intent,1526)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1526)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                val r = data?.getIntExtra("RESULT",8)
                result?.text = "Result : ${r.toString()}"

            }
        }


    }
}
