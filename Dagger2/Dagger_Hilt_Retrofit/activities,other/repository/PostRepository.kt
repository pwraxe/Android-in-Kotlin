package com.example.dagger2study.repository

import com.example.dagger2study.Network.PostServiceImpl
import com.example.dagger2study.data.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PostRepository @Inject constructor(private val postServiceImpl: PostServiceImpl) {

    fun getPost() : Flow<List<Post>> = flow{
        val response = postServiceImpl.getPost()
        emit(response)
    }.flowOn(Dispatchers.IO)
}