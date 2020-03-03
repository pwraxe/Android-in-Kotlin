package com.example.axebank

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.StringBuilder

class History : AppCompatActivity() {

    private var historySetterText : TextView? = null
    val helper = DatabaseHelper(this)
    private var customer_ID : Int = 0

    private var bankName : String? = null
    private var ifscCode : String? = null
    private var accountNumber : String?  = null
    private var amount : String? = null
    private var message : String? = null
    private var dateTime : String? = null

    private var buffer : StringBuffer? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        historySetterText = findViewById(R.id.historySetter_id)

        val intent = intent
        customer_ID = intent.getIntExtra("CID",0)

        var cursor = helper.getHistory(customer_ID)
        if (cursor?.count == 0)
            historySetterText?.text = "No History Found"
        buffer = StringBuffer()
        while (cursor!!.moveToNext())
        {

            buffer?.append("Bank Name : ${cursor.getString(1)} \n")
            buffer?.append("IFSC Code : ${cursor.getString(2)} \n")
            buffer?.append("Account No: ${cursor.getString(3)} \n")
            buffer?.append("Amount    : ${cursor.getString(4)} \n")
            buffer?.append("Message   : ${cursor.getString(5)} \n")
            buffer?.append("Date Time : ${cursor.getString(6)} \n\n\n")

            historySetterText?.text = buffer.toString()

        }



    }
}
