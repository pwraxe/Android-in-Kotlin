package com.example.roomdb_practice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdb_practice.room_stuffs.UserDao
import com.example.roomdb_practice.room_stuffs.UserRepository
import com.example.roomdb_practice.room_stuffs.UsersData
import com.example.roomdb_practice.room_stuffs.UsersRoomDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private var userDao: UserDao = UsersRoomDatabase.getRoomInstance(application).userDao()
    private var repository: UserRepository

    var usersAllData : LiveData<List<UsersData>>

    init {
        repository = UserRepository(userDao)
        usersAllData = repository.getData
    }

    fun insertData(usersData : UsersData){

        viewModelScope.launch {
            repository.insertData(usersData)
        }
    }

    fun editData(userData : UsersData){
        viewModelScope.launch {
            repository.editData(userData)
        }
    }

    fun deleteThis(usersData: UsersData){
        viewModelScope.launch {
            repository.deleteThis(usersData)
        }
    }
}