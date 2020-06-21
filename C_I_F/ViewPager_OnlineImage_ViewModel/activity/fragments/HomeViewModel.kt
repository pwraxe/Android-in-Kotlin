package com.example.onlineimageslider.fragments

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.onlineimageslider.R
import com.example.onlineimageslider.retrofit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(context: Context) : ViewModel() {


    private val  job = Job()
    private val scope = CoroutineScope(Dispatchers.Main+job)

    private var _status = MutableLiveData<STATUS>()
    val status : LiveData<STATUS>
        get() = _status


    private var _onlineData =  MutableLiveData<SingleObject>()
    val onlineData : LiveData<SingleObject>
        get() = _onlineData


    private var _listOfImages = MutableLiveData<ArrayList<OnlineImage>>()
    val listOfImages : LiveData<ArrayList<OnlineImage>>
        get() = _listOfImages


    init {

        loadOnlineData()
    }

    private fun loadOnlineData(){
        scope.launch {
            try {

                val singleObj = RetrofitRef.retrofitRef.getOnlineImageAsync().await()
                val hits = singleObj.hits
                Log.e("AXE","Link : ${hits[0].largeImageURL}")
                _listOfImages.value = hits
                _status.value = STATUS.DONE



            }catch (e : Exception){
                _status.value = STATUS.ERROR
                Log.e("AXE","exception : $e")
            }
        }
    }


}

class HomeViewModelFactory(private var context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(context) as T
        }
        throw IllegalArgumentException("Class cannot be Created")
    }
}

class OnlineImageAdapter(var listOfImg: ArrayList<OnlineImage>) :  PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = listOfImg.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        Glide.with(imageView.context)
            .load(listOfImg[position].largeImageURL)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.loading_animated)
            .fitCenter()
            .into(imageView)
        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}

