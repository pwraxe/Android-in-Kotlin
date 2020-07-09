package com.example.roomdb_practice.room_stuffs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UsersData::class),version = 1,exportSchema = false)
abstract class UsersRoomDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE : UsersRoomDatabase? = null

        fun getRoomInstance(context: Context) : UsersRoomDatabase{

            synchronized(this){

                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        UsersRoomDatabase::class.java,
                        "UserDB").build()
                    return INSTANCE as UsersRoomDatabase
                }
                return INSTANCE as UsersRoomDatabase
            }
        }
    }
}