package com.example.broadcast_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class CustomBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Data Received in CustomBroadcast", Toast.LENGTH_LONG).show()
    }
}