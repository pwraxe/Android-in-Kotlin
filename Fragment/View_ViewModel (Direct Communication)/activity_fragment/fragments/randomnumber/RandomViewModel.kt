package randomnumber.example.safeargsfrag.fragments.randomnumber


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import kotlin.random.Random

class RandomViewModel : ViewModel() {

    private var _getRandomNumber = MutableLiveData<Int>()

    val getRandomNumber : LiveData<Int>
        get() = _getRandomNumber

    init {
        _getRandomNumber.value = getRandomNumber()
        Log.e("AXE","Random ViewModel INIT")
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("AXE","Random view model CLEAR")
    }

    fun getSetNextNumber() {
        _getRandomNumber.value = getRandomNumber()
    }

    private fun getRandomNumber() : Int = Random.nextInt(100)











}