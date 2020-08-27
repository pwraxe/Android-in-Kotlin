package com.codexdroid.nativeadsdemo

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class MainViewModel(var app : Application) : AndroidViewModel(app) {



    private var job = Job()
    private var scope = CoroutineScope(Dispatchers.Main+job)

    private var _dataList = MutableLiveData<ArrayList<UserData>>()
    val dataList : LiveData<ArrayList<UserData>>
        get() = _dataList

    private var _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
        get() = _networkState

    init {
        loadOnlineData()
    }

    private fun loadOnlineData(){

        scope.launch {
            val def = UserDataApiService.userApiService.getUserDetailsAsync()
            try {
                val obj = def.await()
                _dataList.value = obj.hits
                _networkState.value = NetworkState.LOADED

            }catch (ex : Exception){
                _networkState.value = NetworkState.ERROR
            }
        }
    }
}


class MainViewModelFactory(var app : Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }


}