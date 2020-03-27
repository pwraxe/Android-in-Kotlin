package com.example.retrofit_fetch_photo

import retrofit2.Call
import retrofit2.http.GET

interface JsonAPI {

    @GET("photos")
    fun getImages() : Call<ArrayList<OnlinePhotos>>

}