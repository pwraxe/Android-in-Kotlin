package com.example.codeinflow_simplenotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Notification received at BroadcastReceiver",Toast.LENGTH_LONG).show()
    }
}