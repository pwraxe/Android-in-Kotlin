package com.example.coroutinesdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private var job = Job()
    private var scope = CoroutineScope(Dispatchers.Main+job)

    private var _randomNumber = MutableLiveData<Int>()
    val randomNumber : LiveData<Int>
        get() = _randomNumber

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        scope.launch {
            _randomNumber.value = generateNumber()
        }
    }

    fun getNumber() = getRandomNumber()

    private suspend fun generateNumber() : Int {
        delay(1000)
        return Random.nextInt(1000)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}

