package com.example.service_demo

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CustomIntentService : Service() {
    private var customBinder : IBinder = CustomBinder()

    inner class CustomBinder : Binder() {
        fun getCustomService() : CustomIntentService {
            return this@CustomIntentService
        }
    }

    // This onStartCommand(...)  method calls when startService(..) method calls
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AXE","On Start Command")
        return super.onStartCommand(intent, flags, startId)
    }

    // This onBind(...) method calls when bindService(..) method calls
    override fun onBind(intent: Intent?): IBinder? {
        Log.e("AXE","on Bind")
        return customBinder
    }

    // This onDestroy() Method calls when stopService() OR unbindService() method call
    override fun onDestroy() {
        Log.e("AXE","on Destroy")
        super.onDestroy()
    }


    override fun unbindService(conn: ServiceConnection) {
        super.unbindService(conn)
        Log.e("AXE","on unbind")
    }
}
