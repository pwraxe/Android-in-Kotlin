package com.example.roomdb_practice.room_stuffs

import androidx.lifecycle.LiveData

class UserRepository (private var userDao: UserDao) {

    var getData : LiveData<List<UsersData>> = userDao.getAllUsersData()

    suspend fun insertData(usersData: UsersData){
        userDao.insertUsersData(usersData)
    }

    suspend fun deleteAll(){
        userDao.deleteAllData()
    }

    suspend fun deleteThis(usersData: UsersData){
        userDao.deleteOnlyThis(usersData)
    }

    suspend fun editData(usersData: UsersData){
        userDao.updateThis(usersData)
    }

}