 package com.example.service_demo

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

 class MainActivity : AppCompatActivity()  {


     private var startButton : Button? = null
     private var stopButton : Button? = null
     private var numberText : TextView? = null
     private var isGeneratorOn = false
     private var randomNumber = 0

     lateinit var handler : Handler

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         Log.e("AXE","Thread ID : ${Thread.currentThread().id}")
         startButton = findViewById(R.id.id_startButton)
         stopButton = findViewById(R.id.id_stopButton)
         numberText = findViewById(R.id.id_textView)

         handler = Handler(applicationContext.mainLooper)


         startButton?.setOnClickListener {

             Thread(Runnable {
                 generator()
             }).start()
         }
         stopButton?.setOnClickListener {
             isGeneratorOn = false
             numberText?.text = "Stop"
             Log.e("AXE","----------------PAUSE--------------")
         }
     }

     private fun generator() {
        isGeneratorOn = true
        while (isGeneratorOn) {
            try {
                randomNumber = Random().nextInt(100)+1
                Log.e("AXE","Random Number : $randomNumber")
                Thread.sleep(1000)
            }catch (ex:InterruptedException){ ex.message }
            numberText?.post {
                numberText?.text = "Random Number : $randomNumber"
            }
        }


     }
 }
