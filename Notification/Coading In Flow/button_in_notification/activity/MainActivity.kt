package com.example.codeinflow_simplenotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private var notifyButton : Button? = null
    var manager : NotificationManagerCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = NotificationManagerCompat.from(this)

        notifyButton = findViewById(R.id.id_button)

        notifyButton?.setOnClickListener {



            val intent = Intent(this,MainActivity::class.java)
            val pendInt = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val intent2 = Intent(this,NotificationReceiver::class.java)
            val pend2 = PendingIntent.getBroadcast(this,1,intent2,PendingIntent.FLAG_UPDATE_CURRENT)


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val notification = NotificationCompat.Builder(this,MyNotification.ChannelId)
                notification.color = Color.RED

                notification.addAction(R.drawable.ic_bell,"Button-1",pend2)
                notification.setContentIntent(pendInt)


                notification.priority = NotificationManager.IMPORTANCE_HIGH
                notification.setCategory(NotificationCompat.CATEGORY_MESSAGE)
                notification.setContentText("This is TEXT")
                notification.setContentTitle("This is TITLE")
                notification.setOnlyAlertOnce(true)
                notification.setSmallIcon(R.drawable.ic_bell)
                notification.setSubText("This is SUB-TEXT")
                notification.setAutoCancel(true)
                notification.build()

                manager?.notify(1,notification.build())

            }
        }

    }
}