package com.example.expandablenotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyChannel : Application()
{

    companion object{
        const val Channel_id = "com.example.expandablenotification"
        const val Channel_name = "Axe Channel"
        const val Channel_desc = "For Notify User"
    }
    var channel : NotificationChannel? = null
    var manager : NotificationManager? = null

    override fun onCreate() {
        super.onCreate()

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(Channel_id, Channel_name,NotificationManager.IMPORTANCE_HIGH)
            channel?.description = Channel_desc
            channel?.enableLights(true)
            channel?.enableVibration(true)
            channel?.shouldShowLights()
            channel?.shouldVibrate()
            manager?.createNotificationChannel(channel!!)
        }


    }

}