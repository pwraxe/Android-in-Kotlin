package com.example.retrofit_viewmodel_databinding.detailsfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_viewmodel_databinding.retrofit.UserData

class DetailViewModelFactory(var userOjb : UserData) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java))  {
            return DetailViewModel(userOjb) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}