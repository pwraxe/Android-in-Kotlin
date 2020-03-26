package com.example.retrofit_fetchonlinecomments

import retrofit2.Call
import retrofit2.http.GET

interface JsonAPI
{
    @GET("comments")
    fun getComment() : Call<ArrayList<OnlineComments>>
}