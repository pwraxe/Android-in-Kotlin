package com.example.onlineimageslider.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


enum class STATUS { ERROR, DONE, LOADING}


private const val BASE_URL = "https://pixabay.com/api/"


val retrofit: Retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitAPI {

    @GET("?key=15745555-d342d00f85f2ab3998a613c5e&pretty=true")
    fun getOnlineImageAsync() : Deferred<SingleObject>
}

object RetrofitRef{
    val retrofitRef = retrofit.create(RetrofitAPI::class.java)
}