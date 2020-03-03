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

 class MainActivity : AppCompatActivity() {

     private var numberText : TextView? = null
     private var startButton : Button? = null
     private var stopButton : Button? = null
     private var bindServiceButton : Button? = null
     private var unbindService : Button? = null
     private var setNumberButton : Button? = null

     var isBind = false
     lateinit var connection : ServiceConnection
     lateinit var ms : CustomIntentService

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         Log.e("AXE","Current Thread in onCreate : ${Thread.currentThread().id}")
         val intent = Intent(applicationContext, CustomIntentService::class.java)

         numberText = findViewById(R.id.id_randomNumberText)
         startButton = findViewById(R.id.id_startButton)
         stopButton = findViewById(R.id.id_stopButton)
         bindServiceButton = findViewById(R.id.id_bindService)
         unbindService = findViewById(R.id.id_unBindService)
         setNumberButton = findViewById(R.id.id_setNumber)

         startButton?.setOnClickListener {
             startService(intent)
         }

         stopButton?.setOnClickListener {
             stopService(intent)
         }

         bindServiceButton?.setOnClickListener {
             connection = object : ServiceConnection{

                 override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                     val myService = service as CustomIntentService.CustomService
                     ms = myService.getCustomService()
                     isBind = true
                 }
                 override fun onServiceDisconnected(name: ComponentName?) {
                     unbindService(connection)
                     isBind = false
                 }
             }

             bindService(intent,connection,Context.BIND_AUTO_CREATE)
         }

         unbindService?.setOnClickListener {
             unbindService(connection)
             isBind = false
         }

         setNumberButton?.setOnClickListener {
             if(isBind)
                 numberText?.text = "Random Number : ${ms.getAnyNumber()}"
             else
                 numberText?.text = "Service not Bound"

         }

     }
 }