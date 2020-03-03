package com.example.notificationdemo

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {


    private var button  : Button? = null


    private var channelId = "com.example.notificationdemo"
    private var channelName = "Axe_Channel"
    private var channelDesc = "AxeChannelNotification"

    private var channel : NotificationChannel? = null
    private var manager : NotificationManager?= null


    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
            channel?.description = channelDesc
            channel?.enableLights(true)
            channel?.enableVibration(true)
            channel?.shouldVibrate()


        }

        button?.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            val pendInt = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(this,channelId)
            builder.priority = NotificationManagerCompat.IMPORTANCE_HIGH
            builder.setContentTitle("Notification Content Title")
            builder.setContentText("Notification Content Text")
            builder.setSmallIcon(R.drawable.ic_fingerprint)
           // builder.setTimeoutAfter(5000)
            builder.setContentIntent(pendInt)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager?.createNotificationChannel(channel!!)
                manager?.notify(10,builder.build())
            }









        }


    }

}