package com.example.storeretrive_imagesfromfirebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage



/*
* TODO :   add following code to manifest file     for more check this link => https://firebase.google.com/docs/cloud-messaging/android/receive
*  *
        <service android:name=".NotificationService"
            android:exported="false">
            <intent-filter>
                <action android:name="android.intent.action.RESPOND_VIA_MESSAGE" />
                <action android:name="com.google.firebase.MESSAGING_EVENT"/>
            </intent-filter>
        </service>
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_icon"
            android:resource="@drawable/ic_eye" />
        <!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
             notification message. See README(https://goo.gl/6BKBk7) for more. -->
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_color"
            android:resource="@color/colorAccent" />

 *
 * TODO :  try to send notification from firebase console
 *
* */








class NotificationService : FirebaseMessagingService(){

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("AXE","new token : $token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMsg: RemoteMessage) {
        super.onMessageReceived(remoteMsg)

        Log.e("AXE","Notification  :  ${remoteMsg.notification}")       // Notification  :  com.google.firebase.messaging.RemoteMessage$Notification@facc10c
        Log.e("AXE","Collapse Key  : ${remoteMsg.collapseKey}")         // Collapse Key  : com.example.storeretrive_imagesfromfirebase
        Log.e("AXE","Data   :  ${remoteMsg.data}")                      // Data   :  {name=alex Joe}
        Log.e("AXE","From  :  ${remoteMsg.from}")                       // From  :  821458317059
        Log.e("AXE","Message ID  :  ${remoteMsg.messageId}")            // Message ID  :  0:1593323446168676%a810c7d1a810c7d1
        Log.e("AXE","Message Type  :  ${remoteMsg.messageType}")        // Message Type  :  null
        Log.e("AXE","originalPriority  :  ${remoteMsg.originalPriority}")   // originalPriority  :  1
        Log.e("AXE","priority  :  ${remoteMsg.priority}")                   // sentTime  :  1593323445955
        Log.e("AXE","senderId  :  ${remoteMsg.senderId}")               // senderId  :  821458317059
        Log.e("AXE","sentTime  :  ${remoteMsg.sentTime}")               // sentTime  :  1593323445955
        Log.e("AXE","to  :  ${remoteMsg.to}")                           // to  :  null
        Log.e("AXE","ttl  :  ${remoteMsg.ttl}")                         // ttl  :  2419200

        Log.e("AXE","Notification Body  :  ${remoteMsg.notification?.body}")                // Notification Body  :  A notification message goes here
        Log.e("AXE","Notification bodyLocalizationArgs  :   ${remoteMsg.notification?.bodyLocalizationArgs}")       // Notification bodyLocalizationArgs  :   null
        Log.e("AXE","Notification bodyLocalizationKey  : ${remoteMsg.notification?.bodyLocalizationKey}")       // Notification bodyLocalizationKey  : null
        Log.e("AXE","Notification channelId  :  ${remoteMsg.notification?.channelId}")                          // Notification channelId  :  com.example.storeretrive_imagesfromfirebase
        Log.e("AXE","Notification ClickAction  : ${remoteMsg.notification?.clickAction}")                       // Notification ClickAction  : null
        Log.e("AXE","Notification Color  :  ${remoteMsg.notification?.color}")                                  // Notification Color  :  null
        Log.e("AXE","Notification Default Light Setting  :  ${remoteMsg.notification?.defaultLightSettings}")   // Notification Default Light Setting  :  false
        Log.e("AXE","Notification Default Sound :  ${remoteMsg.notification?.defaultSound}")                    // Notification Default Sound :  false
        Log.e("AXE","Notification  defaultVibrateSettings  :  ${remoteMsg.notification?.defaultVibrateSettings}") // Notification  defaultVibrateSettings  :  false

        Log.e("AXE","Notification eventTime  :  ${remoteMsg.notification?.eventTime}")                          // Notification eventTime  :  null
        Log.e("AXE","Notification icon  : ${remoteMsg.notification?.icon}")             // Notification icon  : null
        Log.e("AXE","Notification imageURL  :  ${remoteMsg.notification?.imageUrl}")       // Notification imageURL  :  null
        Log.e("AXE","Notification light setting :  ${remoteMsg.notification?.lightSettings}")   // Notification light setting :  null
        Log.e("AXE","Notification Link  : ${remoteMsg.notification?.link}")                 // Notification Link  : null
        Log.e("AXE","Notification Local Only : ${remoteMsg.notification?.localOnly}")       // Notification Local Only : false
        Log.e("AXE","NotificationCount  :  ${remoteMsg.notification?.notificationCount}")   // NotificationCount  :  null
        Log.e("AXE","Notification defaultVibrateSettings  :  ${remoteMsg.notification?.defaultVibrateSettings}")    // Notification defaultVibrateSettings  :  false
        Log.e("AXE","Notification Sticky  :  ${remoteMsg.notification?.sticky}")            // Notification Sticky  :  false
        Log.e("AXE","Notification Priority  : ${remoteMsg.notification?.notificationPriority}")  //  Notification Priority  : null
        Log.e("AXE","Notification Sound : ${remoteMsg.notification?.sound}")                // Notification Sound : default
        Log.e("AXE","Notification Tag  : ${remoteMsg.notification?.tag}")                   // Notification Tag  : campaign_collapse_key_1429653273077767821
        Log.e("AXE","Notification Ticker  : ${remoteMsg.notification?.ticker}")             // Notification Ticker  : null
        Log.e("AXE","Notification title  :  ${remoteMsg.notification?.title}")              // Notification title  :  Notification Title
        Log.e("AXE","Notification titleLocalizationKey  :  ${remoteMsg.notification?.titleLocalizationKey}")  // Notification titleLocalizationKey  :  null
        Log.e("AXE","Notification visibility  : ${remoteMsg.notification?.visibility}")         // Notification visibility  : null

        Log.e("AXE","Notification toIntent type : ${remoteMsg.toIntent().type}")        // Notification toIntent type : null
        Log.e("AXE","Notification toIntent data : ${remoteMsg.toIntent().data}")        // Notification toIntent data : null

        Log.e("AXE","Notification toIntent extras : ${remoteMsg.toIntent().extras}")  /*  following is output of this line

         Bundle
         [
            {
                 google.c.a.c_l=myNotification,
                 google.c.a.udt=0,
                 google.delivered_priority=high,
                 google.sent_time=1593323445955,
                 gcm.notification.android_channel_id=com.example.storeretrive_imagesfromfirebase,
                 google.ttl=2419200,
                 google.original_priority=high,
                 gcm.notification.e=1,
                 google.c.a.c_id=1429653273077767821,
                 google.c.a.ts=1593323445,
                 gcm.notification.sound=default,
                 gcm.notification.title=Notification Title,
                 gcm.n.e=1,
                 from=821458317059,
                 name=alex Joe,
                 gcm.notification.sound2=default,
                 google.message_id=0:1593323446168676%a810c7d1a810c7d1,
                 gcm.notification.body=A notification message goes here,
                 google.c.a.e=1,
                 google.c.sender.id=821458317059,
                 gcm.notification.tag=campaign_collapse_key_1429653273077767821,
                 collapse_key=com.example.storeretrive_imagesfromfirebase
            }
         ]
         */

        Log.e("AXE","Notification toIntent scheme : ${remoteMsg.toIntent().scheme}")    // Notification toIntent scheme : null

        showNotification(remoteMsg.notification?.body,remoteMsg.notification?.title)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(body : String?, title : String?) {

        val channelId = "com.example.storeretrive_imagesfromfirebase"
        val channelName = "Axe_Channel"
        val channelDesc = "AxeChannelNotification"

        val channel : NotificationChannel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
        val manager : NotificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        channel.description = channelDesc
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.shouldVibrate()


        val pendingIntent = PendingIntent.getActivity(this,1234, Intent(this,MainActivity::class.java),0)

        val notification = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_sad_face)
            .setAutoCancel(true)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(2000))
            .build()

        manager.createNotificationChannel(channel)
        manager.notify(12,notification)
    }

    override fun onMessageSent(p0: String) {
        super.onMessageSent(p0)
    }
}
