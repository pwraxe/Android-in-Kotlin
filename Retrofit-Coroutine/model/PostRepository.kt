package com.codexdroid.coroutinestudy.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository(private val postRequest: PostRequest) {

    fun getPost() = flow {
        emit(Resource.loading())
        val posts = postRequest.getPosts()
        emit(Resource.success(posts))
    }.catch {
        emit(Resource.failed(it.message!!))
    }.flowOn(Dispatchers.IO)
}