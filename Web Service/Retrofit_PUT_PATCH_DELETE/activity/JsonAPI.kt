package com.example.retrofit_put_patch_delete

import retrofit2.Call
import retrofit2.http.*

interface JsonAPI {

    @PUT("posts/{id}")
    fun putCustomData(@Path("id") id : Int, @Body onlineData : OnlineData) : Call<OnlineData>

    @PATCH("posts/{id}")
    fun patchCustomData(@Path("id") id : Int , @Body saveData : OnlineData) : Call<OnlineData>

    @DELETE("posts/{id}")
    fun deleteCustomData(@Path("id") id : Int) : Call<Void>
}