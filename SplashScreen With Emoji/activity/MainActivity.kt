package com.example.splashscreendemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dontLaugh = findViewById<TextView>(R.id.dontLaugh_id)
        val emojiCode : Int = 0x1F606

        dontLaugh.text = "Wait..! That's not your fingerprint ${getEmoji(emojiCode)}"


    }

    fun getEmoji(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}


kotlin.NotImplementedError: An operation is not implemented: get Registered user id and password from database and check then move to login page
        at com.example.axebank.MainActivity$onCreate$2.onClick(MainActivity.kt:43)