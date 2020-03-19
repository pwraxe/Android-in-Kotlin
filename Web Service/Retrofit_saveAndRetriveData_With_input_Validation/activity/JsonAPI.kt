package com.example.retrofit_savedata_method2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JsonAPI {

    @POST("posts")
    fun saveData( @Body saveData : SaveDataOnline ) : Call<SaveDataOnline>
}