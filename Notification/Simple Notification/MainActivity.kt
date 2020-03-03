package com.example.notificationdemo

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {


    private var button  : Button? = null
    private var channel : NotificationChannel? = null
    private var manager : NotificationManager? = null

    private val CHANNEL_ID = "AXE_1011"
    private val CHANNEL_NAME = "AXE_CHANNEL"
    private val CHANNEL_DESC = "Notification by Axe"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //val managerCom = NotificationManagerCompat.from(this)


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH)
            channel?.description = CHANNEL_DESC
            channel?.enableLights(true)
            channel?.lightColor = Color.GREEN
            channel?.enableVibration(true)

            manager?.createNotificationChannel(channel!!)

        }

        button?.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val pendInt = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(this,CHANNEL_ID)
                builder.setContentTitle("Notification Title")
                builder.setContentText("This is Notification From My Notification Demo App")
                builder.setSmallIcon(R.drawable.ic_fingerprint)
                builder.setContentIntent(pendInt)
                builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE)
                builder.priority = NotificationManagerCompat.IMPORTANCE_DEFAULT

                Handler().postDelayed({
                    NotificationManagerCompat.from(this).notify(1,builder.build())
                },3000)







        }

    }
}
