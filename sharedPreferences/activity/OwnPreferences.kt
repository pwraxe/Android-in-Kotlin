package com.example.sharedpreferencesdemo

import android.content.Context

class OwnPreferences (context: Context){

    val PREFERENCE_NAME = "PreAxe"
    val COUNT = "APP_COUNT"

    val pref = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun getCount() : Int = pref.getInt(COUNT,0)

    fun setCount(count : Int)
    {
        val change = pref.edit()
        change.putInt(COUNT,count)
        change.apply()
    }


}