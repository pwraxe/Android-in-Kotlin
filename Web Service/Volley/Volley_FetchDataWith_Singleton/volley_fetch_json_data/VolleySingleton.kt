package com.example.volley_fetch_json_data

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
class VolleySingleton private constructor(context:Context) {

    val requestQueue:RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        private var mInstance:VolleySingleton ? = null

        @Synchronized fun getInstance(context:Context):VolleySingleton? {
            if (mInstance == null) {
                mInstance = VolleySingleton(context)
            }
            return mInstance
        }
    }
}


/*
* calling this class like this
*           val mQueue:RequestQueue? = VolleySingleton.getInstance(this)?.requestQueue
*
* */