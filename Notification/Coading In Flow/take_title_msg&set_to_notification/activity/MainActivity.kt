package com.example.codeinflow_simplenotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    private var et_title : EditText? = null
    private var et_message : EditText? = null
    private var notifyButton : Button? = null

    private var manager : NotificationManagerCompat? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_title = findViewById(R.id.id_title)
        et_message = findViewById(R.id.id_message)
        notifyButton = findViewById(R.id.id_button)
        manager = NotificationManagerCompat.from(this)

        notifyButton?.setOnClickListener {
            val title = et_title?.text.toString().trim()
            val message = et_message?.text.toString().trim()

            val intent = Intent(this,MainActivity::class.java)
            val pendInt = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val notification = NotificationCompat.Builder(this, MyNotification.CHANNEL_ID)

            notification.priority = NotificationManager.IMPORTANCE_DEFAULT
            notification.setChannelId(MyNotification.CHANNEL_ID)
            notification.setContentIntent(pendInt)
            notification.setContentText(message)
            notification.setContentTitle(title)
            notification.setSmallIcon(R.drawable.ic_bell)

            notification.setCategory(NotificationCompat.CATEGORY_ALARM)


            manager?.notify(1,notification.build())


        }

    }
}
