package com.codexdroid.coroutinestudy.model

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private var postRepository: PostRepository) : ViewModel() {

    private var _posts = MutableLiveData<Resource<List<Post>>>()
    val posts : LiveData<Resource<List<Post>>> = _posts

    fun getPosts() = viewModelScope.launch {
        postRepository.getPost().collect{
            _posts.value = it
        }
    }
}




class MainViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(postRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}