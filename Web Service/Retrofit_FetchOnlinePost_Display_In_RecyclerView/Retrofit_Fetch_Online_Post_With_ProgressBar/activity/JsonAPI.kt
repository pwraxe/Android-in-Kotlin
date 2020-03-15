package com.example.retrofit_fetchonlineposts

import retrofit2.Call
import retrofit2.http.GET

interface JsonAPI  {

    @GET("posts")
    fun getPosts() : Call<ArrayList<OnlineData>>

}