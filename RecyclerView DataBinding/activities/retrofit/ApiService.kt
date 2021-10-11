package com.example.recyclerviewbindadapter.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


data class MarsPhotos(var id: String, var img_src:String)

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService{

    @GET("photos")
    suspend fun getPhotos() : List<MarsPhotos>
}

object MarsApi{
    val retrofitService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}