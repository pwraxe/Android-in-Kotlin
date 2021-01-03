package com.codexdroid.workmanagerstudy

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.math.cos
import kotlin.math.sin

class BackgroundWork(context: Context, param : WorkerParameters) : Worker(context,param) {

    override fun doWork(): Result {

        return try {

            /**
            // received data here
            val dataFromForeground = inputData.getInt("INT_VAL",0)
            Log.e("AXE","Data Received : $dataFromForeground")
            repeat(dataFromForeground){
                Log.e("AXE","doWork :  $it")
                Thread.sleep(1000)
             }

            */

            //send data from background thread to main thread
            val tan = (sin(30.0)*sin(30.0)) + (cos(45.0)* cos(45.0))
            val data : Data = Data.Builder()
                    .putDouble("TAN_VAL",tan)
                    .build()
            Result.success(data)

        }catch (ex : Exception){
            Result.failure()
        }

    }
}