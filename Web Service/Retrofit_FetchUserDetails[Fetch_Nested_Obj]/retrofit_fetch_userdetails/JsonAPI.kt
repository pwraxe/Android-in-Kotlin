package com.example.retrofit_fetch_userdetails

import retrofit2.Call
import retrofit2.http.GET

interface JsonAPI {

    @GET("users")
    fun getUsersData() : Call<ArrayList<UsersData>>

}