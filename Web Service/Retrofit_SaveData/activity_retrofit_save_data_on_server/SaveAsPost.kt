package com.example.retrofit_save_data_on_server

class SaveAsPost (
    private var Id : Int,
    private var userId : Int,
    private var title : String,
    private var body : String
){

    fun getId() : Int = Id
    fun getUserId() : Int = userId
    fun getTitle() : String = title
    fun getBody() : String = body

}