package com.example.recyclerviewbindadapter.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class NetworkStatus { LOADING, DONE, ERROR}

class MainViewModel : ViewModel() {

    private var _status = MutableLiveData<NetworkStatus>()
    val status: LiveData<NetworkStatus> = _status

    private var _photos = MutableLiveData<List<MarsPhotos>>()
    val photos : LiveData<List<MarsPhotos>> = _photos

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            _status.value = NetworkStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = NetworkStatus.DONE
            }catch (ex : Exception){
                Log.e("AXE","Exception : ${ex.message}")
                _status.value = NetworkStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}