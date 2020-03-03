package com.example.customised_listview_cardlayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private val mainText = arrayOf("Education","Mobile","Cloud","Laptop","Watch",
        "Paint","Light","Flight","cake","Moon")

    private val subText = arrayOf( "Education field is expanding its area around the world",
        "Mobile is considered as hand hold mini computer",
        "Cloud is most secure and unlimited storage in the world",
        "Laptop is major device of Mobile which is movable in nature",
        "It is fact that watch is been used by almost everyone",
        "Painting is an imagination which can be drawn on a white paper",
        "Human can't live without light so much, thanks to nikola tesla",
        "Everyone has a dream to travel via flight",
        "Birth day is yours and others takes party",
        "India fail to land Vikram softly but we got success"
    )

    private val gallery = arrayOf(
        R.drawable.education,R.drawable.mobile,
        R.drawable.cloud,R.drawable.laptop,
        R.drawable.watch,R.drawable.paint,
        R.drawable.light,R.drawable.flight,
        R.drawable.cake,R.drawable.moon

    )

    private var listview : ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listview = findViewById(R.id.listView_id)

        val myOwnAdapter = CustomAdapter(this,mainText,subText,gallery)
        listview?.adapter = myOwnAdapter

        listview!!.setOnItemClickListener { parent, view, position, _ ->
            val subLine = view.findViewById<TextView>(R.id.subText)

            val small = subLine.text
            val bigText = parent.getItemAtPosition(position).toString()

            val intent = Intent(this,ViewFullData::class.java)
            intent.putExtra("small",small)
            intent.putExtra("big",bigText)
            intent.putExtra("pic",gallery[position])
            startActivity(intent)

        }

    }
}
