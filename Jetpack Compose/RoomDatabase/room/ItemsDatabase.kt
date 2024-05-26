package com.codexdroid.roomdatabasestudy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Items::class], version = 1, exportSchema = false)
abstract class ItemsDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemsDao

    companion object {
        private var instance: ItemsDatabase? = null

        fun getInstance(context: Context): ItemsDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context,ItemsDatabase::class.java,"item_database")
                    .build().also { instance = it }
            }
        }
    }
}