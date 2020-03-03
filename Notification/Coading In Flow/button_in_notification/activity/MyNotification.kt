package com.example.codeinflow_simplenotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat

class MyNotification : Application() {


    companion object{
       const val ChannelId = "com.example.codeinflow_simplenotification"
       const val ChannelName = "AxeChannel"
       const val ChannelDesc = "This is Channel Description"
    }


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannel(ChannelId, ChannelName,NotificationManager.IMPORTANCE_HIGH)
            channel.description = ChannelDesc
            channel.enableVibration(true)
            channel.enableLights(true)
            channel.importance = NotificationManager.IMPORTANCE_HIGH
            channel.shouldVibrate()

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)


        }

    }

}