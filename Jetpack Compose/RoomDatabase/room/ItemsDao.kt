package com.codexdroid.roomdatabasestudy.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(items: Items)

    @Transaction
    @Update
    suspend fun updateItem(items: Items)

    @Transaction
    @Delete
    suspend fun deleteItem(items: Items)

    @Transaction
    @Query("SELECT * FROM items_table WHERE id=:id")
    fun getItem(id: Int): Flow<Items>

    @Query("SELECT * FROM items_table ORDER BY name ASC")
    fun getAllItems(): Flow<List<Items>>

}
