package com.example.axebank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class LoanDetails : AppCompatActivity() {

    var setImage : ImageView? = null
    var setText : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_details)

        setImage = findViewById(R.id.setImageOnNextPage_id)
        setText = findViewById(R.id.setTextOnNextPage_id)

        val intent =  intent
        setImage?.setImageResource(intent.getIntExtra("LI",0))
        setText?.text = intent.getStringExtra("LTT")




    }
}
