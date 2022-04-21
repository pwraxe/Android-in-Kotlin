package com.codexdroid.coroutinestudy

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codexdroid.coroutinestudy.model.*
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(PostRepository(RetrofitBuilder.postRequest))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getPosts()

        mainViewModel.posts.observe(this){
            when(it) {
                is Resource.Failed -> Log.d("AXE","Fail : ${it.message}")
                is Resource.Loading -> Log.d("AXE","Loading...")
                is Resource.Success -> Log.d("AXE","Success : ${Gson().toJson(it.data)}")
            }
        }

    }
}