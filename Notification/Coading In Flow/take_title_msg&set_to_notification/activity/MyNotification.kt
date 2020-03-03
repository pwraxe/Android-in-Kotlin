package com.example.codeinflow_simplenotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyNotification : Application() {

     companion object{
         val CHANNEL_ID = "com.example.codeinflow_simplenotification"
         val CHANNEL_NAME = "Axe_Channel"
         val CHANNEL_DESC = "This is Axe Notification"

     }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
            channel.shouldVibrate()
            channel.description = CHANNEL_DESC
            channel.enableLights(true)
            channel.enableVibration(true)

            val manager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }

}