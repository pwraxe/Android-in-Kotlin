package com.codexdroid.roomdatabasestudy.room

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codexdroid.roomdatabasestudy.ItemsApplication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Entity(tableName = "items_table")
data class Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val experience: Int = 0,
    val designation: String = ""
)

data class UiStates(val id: Int = -1)
//=========================================View Model Provider

object ViewModelProvider {

    val factory = viewModelFactory {
        initializer {
            ItemsViewModel(itemsApplication().repositoryMediator.itemsRepository)
        }
    }
}
fun CreationExtras.itemsApplication():ItemsApplication {
    return this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ItemsApplication
}

//==========================================ViewModel
class ItemsViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<UiStates>(UiStates())
    val uiState: StateFlow<UiStates> = _uiState

    private var _items = MutableStateFlow<List<Items>>(listOf())
    val items: StateFlow<List<Items>> = _items

    private var _item = MutableStateFlow<Items>(Items())
    val item: StateFlow<Items> = _item


    init {
        getAllItems()
    }

    fun setId(id: Int) {
        _uiState.update {
            it.copy(id = id)
        }

        if (id != -1) {
            getItem(id)
        }
    }

    private fun getAllItems() {
        viewModelScope.launch {
            itemsRepository.getAllItems().collectLatest {
                _items.tryEmit(it)
            }
        }
    }

    private fun getItem(id: Int) {
        viewModelScope.launch {
            itemsRepository.getItem(id).collectLatest {
                _item.tryEmit(it)
            }
        }
    }

    fun save(items: Items) {
        viewModelScope.launch {
            itemsRepository.insertItem(items)
        }
    }

    fun update(items: Items) {
        viewModelScope.launch {
            itemsRepository.updateItem(items)
        }
    }

    fun delete(items: Items) {
        viewModelScope.launch {
            itemsRepository.deleteItem(items)
        }
    }
}