package com.example.alarmmanager_with_br

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {


    private var inputBox : EditText? = null
    private var button : Button? = null
    private var timeText : TextView? = null
    private var seconds : Int = 0
    private lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        inputBox = findViewById(R.id.id_box)
        button = findViewById(R.id.id_button)
        timeText = findViewById(R.id.id_text)

        button?.setOnClickListener {

            seconds = inputBox?.text.toString().toInt()

            val intent = Intent(this,CustomAlarm::class.java)
            val pendAct = PendingIntent.getBroadcast(this,1526,intent,0)
            val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+seconds*1000,pendAct)
            countTimer(intent,seconds)

        }

    }
    private fun countTimer(intent: Intent?,seconds : Int){

        object : CountDownTimer(seconds.toLong()*1000,1000){
            override fun onFinish() {
                timeText?.text = ""
                sendBroadcast(intent)
            }
            override fun onTick(millisUntilFinished: Long) {
                timeText?.text = "Wait upto ${(millisUntilFinished/1000).toInt()+1} seconds"
            }
        }.start()
    }
}


class CustomAlarm : BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"Broadcast Received at Runtime",Toast.LENGTH_LONG).show()
        val vibrate = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if(vibrate.hasVibrator()){
            vibrate.vibrate(10000)
        } else {
            Toast.makeText(context,"Vibration is not available in this device",Toast.LENGTH_LONG).show()
        }
    }

}