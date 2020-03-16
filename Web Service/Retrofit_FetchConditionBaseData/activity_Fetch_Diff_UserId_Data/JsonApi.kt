package com.example.retrofit_getpostswithquery

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonApi {

    @GET("posts")
    fun getPosts(
        @Query("userId") user_id : IntArray
    ) : Call<ArrayList<OnlinePosts>>

}