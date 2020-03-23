package com.example.retrofit_put_patch_delete

class OnlineData (

    private var _userId : Int,
    private var _title : String?,
    private var _body : String?
){

     private val id : Int = 0

     fun getID() : Int = id

    val userId : Int
        get() = _userId

    val title : String?
        get() = _title

    val body : String?
        get() = _body

}