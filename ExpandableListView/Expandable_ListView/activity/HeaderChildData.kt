package com.example.expandablelistviewdemo

import android.content.Context

class HeaderChildData (
    private var header : String?,
    private var childs : ArrayList<String>) {


    val _header : String?
        get() = header

    val _childs : ArrayList<String>
        get() = childs

}