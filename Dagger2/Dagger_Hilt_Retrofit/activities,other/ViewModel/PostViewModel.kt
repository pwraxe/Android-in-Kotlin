package com.example.dagger2study.ViewModel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.dagger2study.data.Post
import com.example.dagger2study.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository): ViewModel(),LifecycleObserver {

    val response : LiveData<List<Post>> = postRepository.getPost().catch { fc ->
        Log.e("AXE","PostVM Excetion : $fc")
    }.asLiveData()

}