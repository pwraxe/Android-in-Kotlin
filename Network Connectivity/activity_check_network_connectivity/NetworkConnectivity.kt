package com.example.check_network_connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService


class NetworkConnectivity(private var context: Context) {

    fun isConnected() : Boolean{

        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager.allNetworks.forEach { network ->
                manager.getNetworkInfo(network).apply {
                    if(type == ConnectivityManager.TYPE_WIFI){
                        MainActivity.NetworkState = "You are Currently Connected to Wifi"
                        return true
                    }
                    if(type == ConnectivityManager.TYPE_MOBILE){
                        MainActivity.NetworkState = "You are Currently Connected to Mobile Network"
                        return true
                    }
                }
            }
        }



        return false
    }
}