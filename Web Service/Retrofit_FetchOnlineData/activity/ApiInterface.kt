package com.example.retrofit_practice

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    companion object {
        val baseUrl = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    fun getPosts() : Call<List<OnlinePosts>>


}