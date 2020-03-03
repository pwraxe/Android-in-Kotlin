package com.example.service_demo

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*

class CustomIntentService : Service() {

    var isGeneratorOn = false
    var customService = CustomService() as IBinder
    var random = Random()
    var randomNumber = 0

    override fun onCreate() {
        super.onCreate()
        isGeneratorOn = true
    }

    inner class CustomService : Binder() {
        fun getCustomService() : CustomIntentService {
            return this@CustomIntentService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return customService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(Runnable {
            generateNumber()
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        isGeneratorOn = false
        Log.e("AXE","You Stop to Generate Random Number")
    }

    fun getRandomNum() : Int{
        return randomNumber
    }

    private tailrec fun generateNumber() {
        isGeneratorOn = true
        while (isGeneratorOn) {
            try{
                randomNumber = random.nextInt(100) + 1
                Log.e("AXE","Current Thread ${Thread.currentThread().id} == Random Number $randomNumber")
                Thread.sleep(1000)
            }catch (ex : InterruptedException) { ex.message }
        }
    }


}