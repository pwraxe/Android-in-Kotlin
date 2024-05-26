package com.codexdroid.roomdatabasestudy.room


import android.content.Context
import kotlinx.coroutines.flow.Flow


interface RepositoryObjectCreator {

    val itemsRepository: ItemsRepository
}

class RepositoryMediator(private val context: Context):RepositoryObjectCreator {

    override val itemsRepository: ItemsRepository by lazy {
        LocalStorageRepository(ItemsDatabase.getInstance(context).itemDao())
    }
}


interface ItemsRepository {

    suspend fun insertItem(items: Items)
    suspend fun updateItem(items: Items)
    suspend fun deleteItem(items: Items)

    fun getItem(id:Int): Flow<Items>
    fun getAllItems(): Flow<List<Items>>
}

class LocalStorageRepository(private val itemsDao: ItemsDao): ItemsRepository {

    override suspend fun insertItem(items: Items) {
        itemsDao.insertItem(items)
    }

    override suspend fun updateItem(items: Items) {
        itemsDao.updateItem(items)
    }

    override suspend fun deleteItem(items: Items) {
        itemsDao.deleteItem(items)
    }

    override fun getItem(id: Int): Flow<Items> {
        return itemsDao.getItem(id)
    }

    override fun getAllItems(): Flow<List<Items>> {
        return itemsDao.getAllItems()
    }
}