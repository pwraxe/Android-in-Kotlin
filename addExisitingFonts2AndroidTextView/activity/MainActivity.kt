package com.example.alldemo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{

    private lateinit var firstName : TextView
    private lateinit var lastName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.fname)
        lastName = findViewById(R.id.lname)

        
		// Adding Font OR Applying external downloaded Fonts
		var simple = Typeface.createFromAsset(assets,"fonts/TangerineRegular.ttf")
        var bold = Typeface.createFromAsset(assets,"fonts/TangerineBold.ttf")
        firstName.typeface = simple
        lastName.typeface = bold
    }
}
















