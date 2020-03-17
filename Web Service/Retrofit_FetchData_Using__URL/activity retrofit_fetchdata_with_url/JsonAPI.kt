package com.example.retrofit_fetchdata_with_url

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface JsonAPI {

    @GET
    fun getPosts(@Url url : String) : Call<ArrayList<OnlinePosts>>

}