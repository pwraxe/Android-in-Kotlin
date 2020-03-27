package com.example.retrofit_fetch_custom_data

class MyOnlineData (
    private var name : String?,
    private var age : Int,
    private var email : String?,
    private var mobile : Long ){

    val _name : String?
        get() = name

    val _age : Int
        get() = age

    val _email : String?
        get() = email

    val _mobile : Long
        get() = mobile
}