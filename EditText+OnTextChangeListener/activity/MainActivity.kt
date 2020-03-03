package com.example.edittextwithonchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText1 = findViewById<EditText>(R.id.editText1_id)
        val editText2 = findViewById<EditText>(R.id.editText2_id)
        val editText3 = findViewById<EditText>(R.id.editText3_id)
        val editText4 = findViewById<EditText>(R.id.editText4_id)

        val text1 = findViewById<TextView>(R.id.sample_id)
        val text2 = findViewById<TextView>(R.id.sample2_id)
        val text3 = findViewById<TextView>(R.id.sample3_id)
        val text4 = findViewById<TextView>(R.id.sample4_id)

        val simpleButton = findViewById<Button>(R.id.button1_id)
        val bordrColr = findViewById<Button>(R.id.button2_id)
        val bgStyle = findViewById<Button>(R.id.button3_id)


        simpleButton.setOnClickListener {
            text1.text  = editText1.text.toString()
        }
        bordrColr.setOnClickListener {
            text2.text = editText2.text.toString()
        }
        bgStyle.setOnClickListener {
            text3.text = editText3.text.toString()
        }
        editText4.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                text4.text = s.toString()

            }
        })




    }
}
