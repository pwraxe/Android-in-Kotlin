package com.example.retrofit_viewmodel_databinding.homefragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class HomeViewModelFactory(var app : Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown Class")

    }

}