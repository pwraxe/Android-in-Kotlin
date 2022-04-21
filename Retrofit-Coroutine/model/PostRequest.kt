package com.codexdroid.coroutinestudy.model

import retrofit2.http.GET

interface PostRequest {

    @GET("posts")
    suspend fun getPosts() : List<Post>
}