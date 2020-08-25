package com.codexdroid.mobileadsdemopractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.codexdroid.mobileadsdemopractice.databinding.ActivityMainBinding
import com.codexdroid.mobileadsdemopractice.databinding.LayoutBannerAdBinding
import com.codexdroid.mobileadsdemopractice.databinding.RawLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var dataList = ArrayList<String>()
    private var imgList = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        MobileAds.initialize(this)
        loadData()
        binding.idRecyclerView.adapter = CustomRecyclerView(textList = dataList,imgList = imgList)
    }

    private fun loadData(){
        repeat(100){
            dataList.add(Random.nextInt(10,1000).toString())
            imgList.add(R.mipmap.ic_launcher_round)
        }
    }
}

class CustomRecyclerView(var textList : ArrayList<String>, var imgList : ArrayList<Int>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{


    private val AD_TYPE = 0
    private val ITEMS_LIST = 1

    class AdViewHolder(var adBinding : LayoutBannerAdBinding) : RecyclerView.ViewHolder(adBinding.root){
        //try to implement here
    }

    class ItemViewHolder(var binding : RawLayoutBinding) : RecyclerView.ViewHolder(binding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {

        return if(viewType == AD_TYPE){
            AdViewHolder(LayoutBannerAdBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        } else{
            ItemViewHolder(RawLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Log.e("AXE","item position : ${getItemViewType(position)}")
        when(getItemViewType(position)){
            AD_TYPE -> {

                val adHolder = holder as AdViewHolder
                adHolder.adBinding.idAdView.loadAd(AdRequest.Builder().build())

            }
            ITEMS_LIST -> {

                val itemholder = holder as ItemViewHolder
                itemholder.binding.idRawImageView.setImageResource(imgList[position])
                itemholder.binding.idRawText.text = "Sample Text No. ${textList[position]}"
            }
        }


    }



    override fun getItemCount(): Int = textList.size

    override fun getItemViewType(position: Int): Int {

        return when {
            position  == 0 -> { ITEMS_LIST }

            /**
             * if (position % 8 == 0 )  then ad appear after 8 items
             * if (position % 25 == 0 )  then ad appear after 25 items
             * */
            position % 18 == 0 -> { AD_TYPE }           // same ad will appear after 18 item

            else -> ITEMS_LIST
        }
    }
}