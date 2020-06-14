package com.example.retrofit_viewmodel_databinding.detailsfragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_viewmodel_databinding.retrofit.UserData

class DetailViewModel(var userObj : UserData) : ViewModel()
{

    private var _userData = MutableLiveData<UserData>()
    val userData : LiveData<UserData>
        get() = _userData

    init {
        _userData.value = userObj
    }




}