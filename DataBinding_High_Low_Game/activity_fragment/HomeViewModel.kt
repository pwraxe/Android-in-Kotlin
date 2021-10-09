package com.example.databinding_hi_low_game

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private var _currentAttempted = MutableLiveData(0)
    val currentAttempted : LiveData<Int>
        get() = _currentAttempted

    private var _randomNo = MutableLiveData<Int>()
    val randomNo : LiveData<Int>
        get() = _randomNo


    fun playGame(tag:String){
        _randomNo.value = (0..1000).random()
        _currentAttempted.value = _currentAttempted.value?.plus(1)
        if(tag == "+") {
            if (randomNo.value!! > 500)
                _score.value = _score.value?.plus(100)
            else
                _score.value = _score.value?.minus(100)
        }else {
            if (randomNo.value!! < 500)
                _score.value = _score.value?.plus(100)
            else
                _score.value = _score.value?.minus(100)
        }
    }

    fun resetData(){
        _score.value = 0
        _currentAttempted.value = 0
    }
}