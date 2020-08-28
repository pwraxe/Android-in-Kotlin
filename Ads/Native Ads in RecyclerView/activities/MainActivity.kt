package com.codexdroid.nativeadsdemo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codexdroid.nativeadsdemo.databinding.ActivityMainBinding
import com.codexdroid.nativeadsdemo.databinding.AdLayoutBinding
import com.codexdroid.nativeadsdemo.databinding.RawLayoutBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.UnifiedNativeAdView



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var manager : LinearLayoutManager

    private var isScrolling = false
    private var currentItem : Int = 0
    private var totalItem : Int = 0
    private var scrollItem : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val factory = MainViewModelFactory(requireNotNull(this).application)

        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        manager = LinearLayoutManager(this)

        viewModel.dataList.observe(this){
            binding.idRecyclerView.adapter = CustomRecyclerAdapter(this,it)
        }

        binding.idRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollItem = manager.findFirstVisibleItemPosition()

                if(currentItem+scrollItem == totalItem && isScrolling){
                    isScrolling = false
                    // show progress bar and fetch data
                    //fetchNewData()
                }
            }
        })

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")
    }
}


class CustomRecyclerAdapter(var context: Context, var userData : ArrayList<UserData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val DATA_TYPE = 0
    private val AD_TYPE = 1


    private lateinit var viewHolder : RecyclerView.ViewHolder


    class DataViewHolder(var rawBinding : RawLayoutBinding) : RecyclerView.ViewHolder(rawBinding.root)

    class AdViewHolder (var AdBinding : AdLayoutBinding) : RecyclerView.ViewHolder(AdBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            AD_TYPE -> {
                viewHolder = AdViewHolder(AdLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            DATA_TYPE -> {
                viewHolder = DataViewHolder(RawLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        return viewHolder
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when(getItemViewType(position)){
            AD_TYPE -> {

                val adBinder = holder as AdViewHolder

                val builder = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
                    .forUnifiedNativeAd { unifiedNativeAd ->

                        val adView = LayoutInflater.from(context).inflate(R.layout.ad_layout,null) as UnifiedNativeAdView

                        if(unifiedNativeAd.icon != null){
                            adBinder.AdBinding.idProfilePic.setImageDrawable(unifiedNativeAd.icon.drawable)
                        }else{
                            adBinder.AdBinding.idProfilePic.setImageDrawable(context.getDrawable(R.drawable.ic_person_profile))
                        }

                        adBinder.AdBinding.idHeadLineText.text = unifiedNativeAd.headline
                        adBinder.AdBinding.idSponsorName.text = unifiedNativeAd.advertiser
                        adBinder.AdBinding.idMainImage.setImageDrawable(unifiedNativeAd.images[0].drawable)

                        // -------> imp First Initialized & then used
                        adView.callToActionView = adBinder.AdBinding.idButton
                        (adView.callToActionView as Button).text = unifiedNativeAd.callToAction


                        adView.setNativeAd(unifiedNativeAd)

                    }.build()

                builder.loadAd(AdRequest.Builder().build())

            }
            DATA_TYPE -> {
                val binder = holder as DataViewHolder

                Glide.with(holder.itemView.context)
                    .load(userData[position].userImageURL)
                    .into(binder.rawBinding.idRawProfilePic)

                binder.rawBinding.idPostOwnerName.text = userData[position].user
                Glide.with(holder.itemView.context)
                    .load(userData[position].largeImageURL)
                    .into(binder.rawBinding.imageView)

                binder.rawBinding.idLikes.text = "${userData[position].likes} Likes"
                binder.rawBinding.idDownloads.text = "${userData[position].downloads} Downloads"
            }
        }
    }

    override fun getItemCount(): Int = userData.size

    override fun getItemViewType(position: Int): Int {

        return when {
            position  == 0 -> { DATA_TYPE }
            /**
             * if (position % 8 == 0 )  then ad appear after 8 items
             * if (position % 25 == 0 )  then ad appear after 25 items
             * */
            position % 3 == 0 -> { AD_TYPE }           // same ad will appear after 18 item

            else -> DATA_TYPE
        }

    }

}
