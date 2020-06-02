package randomnumber.example.safeargsfrag.fragments.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(lastNumber : Int) : ViewModel() {



    private var _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score


    init {
       _score.value = lastNumber
        Log.e("AXE","Score View Model INIT")
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("AXE","Score  View Model CLEAR")
    }
}