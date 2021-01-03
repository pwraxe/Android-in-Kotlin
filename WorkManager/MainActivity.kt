package com.codexdroid.workmanagerstudy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.codexdroid.workmanagerstudy.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.idButton.setOnClickListener { startWork() }
    }

    @SuppressLint("RestrictedApi", "VisibleForTests")
    private fun startWork() {

        val manager = WorkManager.getInstance(this)

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
}