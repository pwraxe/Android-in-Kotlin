package com.example.dagger2study.Network

import com.example.dagger2study.data.Post
import retrofit2.http.GET

interface PostApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>

}