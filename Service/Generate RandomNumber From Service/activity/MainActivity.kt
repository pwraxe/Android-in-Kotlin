 package com.example.service_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

 class MainActivity : AppCompatActivity() {

     var numberText : TextView? = null
     private var startButton : Button? = null
     private var stopButton : Button? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         Log.e("AXE","Current Thread in onCreate : ${Thread.currentThread().id}")
         val intent = Intent(applicationContext, CustomIntentService::class.java)

         numberText = findViewById(R.id.id_randomNumberText)
         startButton = findViewById(R.id.id_startButton)
         stopButton = findViewById(R.id.id_stopButton)


         val connection = object : ServiceConnection{

             override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                 var myService = service as CustomIntentService.CustomService
                 val ms = myService.getCustomService()
                 numberText?.text = "Random Number : ${ms.getRandomNum()}"
             }
             override fun onServiceDisconnected(name: ComponentName?) { }
         }
         startButton?.setOnClickListener {
             startService(intent)
         }

         stopButton?.setOnClickListener {
             stopService(intent)
         }


     }

 }