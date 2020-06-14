package com.example.retrofit_viewmodel_databinding.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_viewmodel_databinding.retrofit.LoadState
import com.example.retrofit_viewmodel_databinding.retrofit.SingleLargeObject
import com.example.retrofit_viewmodel_databinding.retrofit.UserData
import com.example.retrofit_viewmodel_databinding.retrofit.UserDataApiService
import kotlinx.coroutines.*

class HomeViewModel (var app : Application) : AndroidViewModel(app) {

    private var job = Job()
    private var scope = CoroutineScope(Dispatchers.Main+job)

    private var _userData = MutableLiveData<SingleLargeObject>()
    val userData : LiveData<SingleLargeObject>
        get() = _userData

    private var _loadState = MutableLiveData<LoadState>()
    val loadState : LiveData<LoadState>
        get() = _loadState

    private var _userObjArr = MutableLiveData<ArrayList<UserData>>()
    val userObjArr : LiveData<ArrayList<UserData>>
        get() = _userObjArr

    private var _userObject = MutableLiveData<UserData>()
    val userObject : LiveData<UserData>
        get() = _userObject


    private var _navigateToSelectedProperty = MutableLiveData<UserData>()
    val navigateToSelectedProperty : LiveData<UserData>
        get() = _navigateToSelectedProperty






    init {
        loadData()
        Log.e("AXE","loadData call")
    }

    @ExperimentalCoroutinesApi
    private fun loadData(){
        scope.launch {
            val def = UserDataApiService.userApiService .getUserDetailsAsync()
            try {

                // val c = def.getCompleted().hits     //ArrayList<UserData>
                val data = def.await()

                //val x = data.hits
                _userObjArr.value = data.hits
                _loadState.value = LoadState.DONE

            }catch (e : Exception){
                _userData.value = null
                _loadState.value = LoadState.ERROR
            }
        }
    }
}