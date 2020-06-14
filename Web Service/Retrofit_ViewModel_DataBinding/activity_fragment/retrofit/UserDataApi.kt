package com.example.retrofit_viewmodel_databinding.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


enum class LoadState { ERROR, LOADING, DONE}

private const val BASE_URL = "https://pixabay.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UserDataApi {

    @GET("?key=15745555-d342d00f85f2ab3998a613c5e")
    fun getUserDetailsAsync() : Deferred<SingleLargeObject>

}

object UserDataApiService {
    val userApiService : UserDataApi by lazy {
        retrofit.create(UserDataApi::class.java)
    }
}