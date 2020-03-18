package com.example.retrofit_save_data_on_server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JsonAPI {

    @POST("posts")
    fun saveData(@Body saveData : SaveAsPost) : Call<SaveAsPost>

}