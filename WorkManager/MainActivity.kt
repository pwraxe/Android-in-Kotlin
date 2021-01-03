package com.example.workmanagercontinue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.example.workmanagercontinue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.idButton.setOnClickListener {
            //startWork()
            //startWorkCase1()
            //startWorkCase2()
            //startWorkCase3()
            //startWorkCase4()
            startWorkCase5()
        }
    }


    private fun startWorkCase5(){
        val manager = WorkManager.getInstance()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val oneTimeWork = OneTimeWorkRequestBuilder<BackgroundWork>()
            .setConstraints(constraints)
            .build()

        manager.enqueue(oneTimeWork)

        manager.getWorkInfoByIdLiveData(oneTimeWork.id).observe(this,{ workInfo ->
            binding.textView.text = "${workInfo.state}"
        })
    }

    /***
    @SuppressLint("RestrictedApi", "VisibleForTests")
    private fun startWork() {

        val manager = WorkManager.getInstance()

        //Method 1 to create Work Request
        val work1 = OneTimeWorkRequest.from(BackgroundWork::class.java)
        manager.enqueue(work1)

        manager.getWorkInfoByIdLiveData(work1.id).observe(this, {
            Toast.makeText(this, "${it.state}", Toast.LENGTH_SHORT).show()
        })
        //--------------------------------------OR-----------------------------------------

        val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .build()


        //-------------------------------send data
        val inputData = Data.Builder()
                .putInt("INT_VAL",30)
                .build()

        //method 2 for work Request
        val work2 = OneTimeWorkRequestBuilder<BackgroundWork>()
                // you can add new param or constraints here
                .setConstraints(constraint)
                .setInputData(inputData)
                .build()
        manager.enqueue(work2)

        //Observer call when state change
        //we can received data here from background thread (Worker class)
        manager.getWorkInfoByIdLiveData(work2.id).observe(this,{
            binding.textView.text = it.state.name
            Log.e("AXE","Work Info : ${it.state}")
            Log.e("AXE","Data Received :  ${it.outputData.getDouble("TAN_VAL",0.0)}")

        })

        //-------------------------------------send data-----------------------------------------

    }
    **/

    /**
    //Case 1: send data to worker class
    private fun startWorkCase1()
    {

        val manager = WorkManager.getInstance()

        val intArray = intArrayOf(1,2,3,6,5,4,7,8,9)
        val strArray = arrayOf("AAA","BBB","CCC","DDD")
        val doubleArray = doubleArrayOf(1.2,2.3,3.4,4.5,5.6,6.7,7.8)


        val data = Data.Builder()
                .putInt("INT",12345)
                .putBoolean("BOOL",false)
                .putDouble("DOUBLE",3.14)
                .putLong("LONG",9876543210)
                .putString("STR","Hello World!")
                .putIntArray("INT_ARR",intArray)
                .putStringArray("STR_ARR",strArray)
                .putDoubleArray("DUB_ARR",doubleArray)
                .build()


        val onetime = OneTimeWorkRequestBuilder<BackgroundWork>()
                .setInputData(data)
                .build()

        manager.enqueue(onetime)

        manager.getWorkInfoByIdLiveData(onetime.id).observe(this,{ workInfo ->
            Log.e("AXE","WorkInfo : $workInfo")
        })

    }
    */

    /***
    //Case 2: want some data from background class
    private fun startWorkCase2(){

        val manager = WorkManager.getInstance()

        //we write this class becoz, I am not sending any param to background class
        val onetimeWork = OneTimeWorkRequest.from(BackgroundWork::class.java)

        manager.enqueue(onetimeWork)

        manager.getWorkInfoByIdLiveData(onetimeWork.id).observe(this, { workInfo ->

            //received data here
            val data =workInfo.outputData

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

            Log.e("AXE","Data Received at MainActivity from Background on State ${workInfo.state} : $receivedData")

        })
    }
    **/

    /**
    //Case 3: send 2 num to worker class and get addition from Worker class
    private fun startWorkCase3() {

        val manager = WorkManager.getInstance()

        val data = Data.Builder()
            .putInt("NUM1",45)
            .putInt("NUM2",45)
            .build()

        val oneTimeWork = OneTimeWorkRequestBuilder<BackgroundWork>()
            .setInputData(data)
            .build()

        manager.enqueue(oneTimeWork)

        manager.getWorkInfoByIdLiveData(oneTimeWork.id).observe(this,{ workInfo ->

            val addition = workInfo.outputData.getInt("SUM",0)
            Log.e("AXE","Addition from background at ${workInfo.state} : $addition")

        })

    }
    **/

    /***
    //Case 4: get Random Number from background class
    private fun startWorkCase4() {
        val manager = WorkManager.getInstance()

        val oneTimeWork = OneTimeWorkRequestBuilder<BackgroundWork>().build()

        manager.enqueue(oneTimeWork)

        manager.getWorkInfoByIdLiveData(oneTimeWork.id).observe(this,{ workInfo ->

            val randomNumber = workInfo.outputData.getInt("RANDOM_NUM",0)

            Log.e("AXE","Random Number on ${workInfo.state} : $randomNumber")

        })
    }
    **/


}
