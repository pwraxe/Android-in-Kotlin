package com.example.check_network_connectivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    // Note : Add Permission --> <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    private var progress : ProgressBar? = null
    private var connectivityText : TextView? = null


    companion object {
        var NetworkState: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.id_progress)
        connectivityText = findViewById(R.id.id_networdState)

        val isConnected = NetworkConnectivity(this).isConnected()
        if (isConnected) {
            progress?.visibility = View.GONE
            connectivityText?.visibility = View.VISIBLE
            connectivityText?.text = MainActivity.NetworkState
        }else{
            progress?.visibility = View.GONE
            connectivityText?.visibility = View.VISIBLE
            connectivityText?.text = "No Network"
        }
    }
}
