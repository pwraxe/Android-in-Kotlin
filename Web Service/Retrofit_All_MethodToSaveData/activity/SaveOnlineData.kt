package com.example.retrofit_savedata_method3

class SaveOnlineData (

    private var id : Int,
    private var userId : Int,
    private var title : String? ,
    private var body : String? ){


    fun getId() : Int = id
    fun getUserId() : Int = userId
    fun getTitle() : String? = title
    fun getBody () : String? = body

}