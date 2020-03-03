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

     private var numberText : TextView? = null
     private var startButton : Button? = null
     private var stopButton : Button? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         val intent = Intent(applicationContext, CustomIntentService::class.java)

         numberText = findViewById(R.id.id_randomNumberText)
         startButton = findViewById(R.id.id_startButton)
         stopButton = findViewById(R.id.id_stopButton)

         var connections = object  : ServiceConnection{

             override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                 var customSir20 = service as CustomIntentService.CustomBinder
                 var c = customSir20.getCustomService()
                 Log.e("AXE","on Service Connected")
             }
             override fun onServiceDisconnected(name: ComponentName?) {
                 Log.e("AXE","on Service Disconnected")
             }
         }

         startButton?.setOnClickListener {
            startService(intent)            // method calls ==>  onStartCommand()
             // bindService(intent,connections,Context.BIND_AUTO_CREATE)   // method call ==> onBind(), onServiceConnected()
         }
         stopButton?.setOnClickListener {
             stopService(intent)              // method call ==> onDestroy()
             //unbindService(connections)     // method call ==> onDestroy()
         }




     }
 }