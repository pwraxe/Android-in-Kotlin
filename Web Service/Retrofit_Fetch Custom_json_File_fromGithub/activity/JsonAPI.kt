package com.example.retrofit_fetch_custom_data

import retrofit2.Call
import retrofit2.http.GET

interface JsonAPI {

    @GET("mydata.json")
    fun getMyOwnData() : Call<ArrayList<MyOnlineData>>


}