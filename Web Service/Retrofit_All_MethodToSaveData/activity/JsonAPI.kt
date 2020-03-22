package com.example.retrofit_savedata_method3

import retrofit2.Call
import retrofit2.http.*

interface JsonAPI {



    // Method 1 to save data
    @POST("posts")
    fun saveDataOnPosts(@Body saveData : SaveOnlineData) : Call<SaveOnlineData>


    //Method 2 to save Data
    @FormUrlEncoded
    @POST("posts")
    fun saveDataOnPosts(

        @Field("id") id : Int,
        @Field("userId") userId : Int,
        @Field("title") title : String?,
        @Field("body") body : String?

    ) : Call<SaveOnlineData>



    // by sending key-value pair reference to this function
    @FormUrlEncoded
    @POST("posts")
    fun saveDataOnPosts(@FieldMap pairData : Map<String,String>) : Call<SaveOnlineData>




}
