package com.example.expandablenotification

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    private var button : Button? = null
    private var manager : NotificationManagerCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.id_button)
        manager = NotificationManagerCompat.from(this)

        button?.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {

        val intent = Intent(this,MainActivity::class.java)
        val pendInt = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(this,MyChannel.Channel_id)
            notification.setAutoCancel(true)                        // notification remove from notification center when user touch it
            notification.setCategory(Notification.CATEGORY_MESSAGE)
            notification.setChannelId(MyChannel.Channel_id)
            notification.setSmallIcon(R.drawable.ic_fingerprint)
            //notification.setChronometerCountDown(true)

//            notification.setStyle(NotificationCompat.BigTextStyle() // this code makes notification expandable
//                .bigText(resources.getString(R.string.long_text))
//                .setBigContentTitle("Big Content Title")    // when notification expand this title appear
//                .setSummaryText("Big Sub Text"))            // does not display when expand
//

            notification.setStyle(NotificationCompat.BigPictureStyle()
                .bigLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_foreground))
                .bigPicture(BitmapFactory.decodeResource(resources,R.drawable.wall3))
                .setBigContentTitle("Big Content Title for Big Pic Style")
                .setSummaryText("Big pic style summery text"))






            notification.color = ContextCompat.getColor(this,R.color.red)           // !work
            notification.setContentIntent(pendInt)
            notification.setContentText("Sample Text")
            notification.setContentTitle("Sample Title")
            notification.setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.wall3))     // works
            notification.setNumber(1526)
            //notification.setOngoing(true)                 // notification cannot remove from notification center until it finished it works ex: notify when charger plugged
            //notification.setProgress(100,12,true)       // we can put progress bar in notification
            //notification.setSettingsText("Setting Text")

            notification.setSubText("SubText")
            //notification.setTimeoutAfter(2000)
            notification.build()

            manager?.notify(1,notification.build())


        } else {
            Toast.makeText(this,"Notification Not Supported less than oreo",Toast.LENGTH_LONG).show()
        }

    }
}
