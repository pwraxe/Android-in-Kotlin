package com.example.retrofit_fetchonlinecomments

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonAPI
{

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId : Int) : Call<ArrayList<OnlineComments>>

}