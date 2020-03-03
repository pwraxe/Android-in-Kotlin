package com.example.service_demo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import java.util.*
/*
class CustomIntentService : Service() {


    inner class CustomRequestHandler : Handler()  {

        override fun handleMessage(msg: Message) {

            val sendRandomNumber = Message.obtain(null,0)
            sendRandomNumber.arg1 = getRandomNum()
            try {
                msg.replyTo.send(sendRandomNumber)
            }catch (ex : RemoteException) { ex . message }
            super.handleMessage(msg)
        }
    }

    val messenger = Messenger(CustomRequestHandler())


    override fun onBind(intent: Intent?): IBinder? {
        startService(intent)
        return CustomRequestHandler() as IBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
    }

    fun getRandomNum() : Int {
        return Random().nextInt(100)+1
    }

}


 */