package com.example.workmanagercontinue

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlin.Exception

class BackgroundWork(context: Context, param : WorkerParameters) : Worker(context,param) {


    //Case 5: task will execute base on constraints
    override fun doWork(): Result {
        return try {
            repeat(50){
                Thread.sleep(500)
                Log.e("AXE","doWork fun : $it")
            }
            Result.success()
        }catch (ex : Exception){
            Result.failure()
        }
    }


    /**
    //Case 1: send data to worker class
    override fun doWork(): Result {

        return try {

            val data = inputData
            val intData = data.getInt("INT",0)
            val boolData = data.getBoolean("BOOL",false)
            val doubleData = data.getDouble("DOUBLE",0.0)
            val longData = data.getLong("LONG",0L)
            val strData = data.getString("STR")
            val intArrayData = data.getIntArray("INT_ARR")
            val strArrayData = data.getStringArray("STR_ARR")
            val doubleArrayData = data.getDoubleArray("DUB_ARR")


            val receivedData = "\nInt : $intData \n " +
                    "Boolean : $boolData \n " +
                    "Double : $doubleData \n " +
                    "Long : $longData \n " +
                    "String : $strData \n " +
                    "Int Array : ${intArrayData?.toList()} \n " +
                    "String Array : ${strArrayData?.toList()} \n " +
                    "Double Array : ${doubleArrayData?.toList()}"

            Log.e("AXE","Data Received at Background : $receivedData")


            Result.success()

        }catch (ex : Exception){
            Result.failure()
        }
    }
    **/

    /***
    // Case 2: send data to main class
    override fun doWork(): Result {
        return try {

            val intArray = intArrayOf(9,8,7,6,5,4,3,2,1)
            val strArray = arrayOf("XXX","YYY","ZZZ")
            val doubleArray = doubleArrayOf(9.9,8.8,7.7,6.6,5.5,4.4,3.3,2.2,1.1)


            val data = Data.Builder()
                .putInt("INT",987654)
                .putBoolean("BOOL",true)
                .putDouble("DOUBLE",3.145)
                .putLong("LONG",9998877665544)
                .putString("STR","Hello World from Background!")
                .putIntArray("INT_ARR",intArray)
                .putStringArray("STR_ARR",strArray)
                .putDoubleArray("DUB_ARR",doubleArray)
                .build()

            Result.success(data)
        }catch (ex : Exception){
            Result.failure()
        }
    }
    ***/

    /**
        // Case 3: return addition to main class from backout
        override fun doWork(): Result {
        return try {

            val num1 = inputData.getInt("NUM1",0)
            val num2 = inputData.getInt("NUM2",0)

            val sum = num1+num2

            val data = Data.Builder()
            .putInt("SUM",sum)
            .build()

            Result.success(data)
         }catch (ex : Exception){
            Result.failure()
         }
       }
             **/

    /**
        // Case 4: send random number to main class
        override fun doWork(): Result 
        {
            return try {

                val data = Data.Builder()
                .putInt("RANDOM_NUM",Random(100).nextInt())
                .build()
            
                Result.success(data)
            }catch (ex : Exception){
                Result.failure()
            }
        }
     **/


}
