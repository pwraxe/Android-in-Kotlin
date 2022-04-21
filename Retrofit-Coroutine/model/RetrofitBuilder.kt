package com.codexdroid.coroutinestudy.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getInstance() : Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }

    val postRequest = getInstance().create(PostRequest::class.java)
}