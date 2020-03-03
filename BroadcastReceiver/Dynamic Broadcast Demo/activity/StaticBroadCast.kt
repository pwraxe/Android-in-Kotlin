package com.example.staticbroadcastex


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StaticBroadCast : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
       if(intent?.action == Intent.ACTION_POWER_CONNECTED)
           Toast.makeText(context,"Connected",Toast.LENGTH_LONG).show()
        else if(intent?.action == Intent.ACTION_POWER_DISCONNECTED)
           Toast.makeText(context,"Disconnected",Toast.LENGTH_LONG).show()
        else
           Toast.makeText(context,"No Operation Perform",Toast.LENGTH_LONG).show()

    }
}