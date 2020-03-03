package com.example.broadcast_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(){

    private var button : Button? = null
    private var receiver : BroadcastReceiver? = null
    private var context : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(applicationContext,"${intent?.action}",Toast.LENGTH_LONG).show()
            }
        }
        registerReceiver(receiver,filter)

        button = findViewById(R.id.button)
        button?.setOnClickListener {
            sendBroadcast(Intent(context,CustomBroadcast::class.java))
        }
    }
}

_______________________________________________________Other Code in same File_________________________________________________________

package com.example.broadcast_demo


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.util.ArrayList

class MainActivity : AppCompatActivity(){

    private var textView : TextView? = null
    private var button : Button? = null
    private val filter = IntentFilter()
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.text_id)


        filter.addAction(Intent.ACTION_CAMERA_BUTTON)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)      //work
        filter.addAction(Intent.ACTION_POWER_CONNECTED)         //work
        filter.addAction(Intent.ACTION_SEND)
        filter.addAction(Intent.ACTION_VIEW)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)       //work
        filter.addAction(Intent.ACTION_ALL_APPS)
        filter.addAction(Intent.ACTION_ANSWER)
        filter.addAction(Intent.ACTION_APPLICATION_PREFERENCES)
        filter.addAction(Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED)
        filter.addAction(Intent.ACTION_APP_ERROR)
        filter.addAction(Intent.ACTION_ASSIST)
        filter.addAction(Intent.ACTION_ATTACH_DATA)
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)         //work
        filter.addAction(Intent.ACTION_BATTERY_LOW)             //work
        filter.addAction(Intent.ACTION_BATTERY_OKAY)
        filter.addAction(Intent.ACTION_BOOT_COMPLETED)
        filter.addAction(Intent.ACTION_BUG_REPORT)
        filter.addAction(Intent.ACTION_CALL)
        filter.addAction(Intent.ACTION_CALL_BUTTON)
        filter.addAction(Intent.ACTION_CARRIER_SETUP)
        filter.addAction(Intent.ACTION_CHOOSER)
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        filter.addAction(Intent.ACTION_CONFIGURATION_CHANGED)
        filter.addAction(Intent.ACTION_CREATE_DOCUMENT)
        filter.addAction(Intent.ACTION_DATE_CHANGED)
        filter.addAction(Intent.ACTION_DEFAULT)
        filter.addAction(Intent.ACTION_DEFINE)
        filter.addAction(Intent.ACTION_DELETE)
        filter.addAction(Intent.ACTION_DIAL)
        filter.addAction(Intent.ACTION_DOCK_EVENT)
        filter.addAction(Intent.ACTION_DREAMING_STARTED)
        filter.addAction(Intent.ACTION_DREAMING_STOPPED)
        filter.addAction(Intent.ACTION_EDIT)
        filter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE)
        filter.addAction(Intent.ACTION_FACTORY_TEST)
        filter.addAction(Intent.ACTION_GET_RESTRICTION_ENTRIES)
        filter.addAction(Intent.ACTION_GTALK_SERVICE_DISCONNECTED)
        filter.addAction(Intent.ACTION_GTALK_SERVICE_CONNECTED)
        filter.addAction(Intent.ACTION_HEADSET_PLUG)            //work
        filter.addAction(Intent.ACTION_INPUT_METHOD_CHANGED)
        filter.addAction(Intent.ACTION_INSERT)
        filter.addAction(Intent.ACTION_INSERT_OR_EDIT)
        filter.addAction(Intent.ACTION_INSTALL_FAILURE)
        filter.addAction(Intent.ACTION_LOCALE_CHANGED)
        filter.addAction(Intent.ACTION_LOCKED_BOOT_COMPLETED)
        filter.addAction(Intent.ACTION_MAIN)
        filter.addAction(Intent.ACTION_MANAGE_NETWORK_USAGE)  //work
        filter.addAction(Intent.ACTION_MEDIA_BUTTON)
        filter.addAction(Intent.ACTION_MEDIA_BUTTON)
        filter.addAction(Intent.ACTION_MEDIA_CHECKING)
        filter.addAction(Intent.ACTION_MEDIA_EJECT)
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED)
        filter.addAction(Intent.ACTION_MEDIA_REMOVED)
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED)
        filter.addAction(Intent.ACTION_MEDIA_SHARED)
        filter.addAction(Intent.ACTION_SEND_MULTIPLE)

        val br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val list = ArrayList<String>()
                list.add(intent?.action.toString())
                Log.e("AXE","$list")
                Toast.makeText(applicationContext,intent?.action.toString(),Toast.LENGTH_LONG).show()
            }
        }
        registerReceiver(br,filter)

        button?.setOnClickListener {

            textView?.text = "Wait for 3 seconds"
            var t = object : CountDownTimer(5000,1000){
                override fun onFinish() {
                    textView?.text = ""
                }
                override fun onTick(millisUntilFinished: Long) {
                    textView?.text = "Wait for ${(millisUntilFinished/1000).toInt()+1} seconds"
                }
            }
            t.start()
            Handler().postDelayed({
                sendBroadcast(Intent(this,CustomBroadcast::class.java))
            },5000)
        }
    }
}