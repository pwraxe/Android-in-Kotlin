package com.example.roomdb_practice.room_stuffs

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersData(usersData: UsersData)

    @Query("SELECT * FROM UsersData ORDER BY u_id ASC")
    fun getAllUsersData() : LiveData<List<UsersData>>

    @Query("DELETE FROM UsersData")
    suspend fun deleteAllData()

    @Update
    suspend fun updateThis(usersData: UsersData)

    //---------- above everything is perfect


    @Delete
    suspend fun deleteOnlyThis(usersData: UsersData)
}