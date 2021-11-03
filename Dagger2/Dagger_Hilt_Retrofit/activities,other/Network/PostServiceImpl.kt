package com.example.dagger2study.Network

import com.example.dagger2study.data.Post
import javax.inject.Inject

class PostServiceImpl @Inject constructor(private val postApiService: PostApiService) {

    suspend fun getPost() : List<Post> = postApiService.getPost()

}