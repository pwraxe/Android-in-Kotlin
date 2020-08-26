package com.codexdroid.nativeadsdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codexdroid.nativeadsdemo.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var uniNativeAd: UnifiedNativeAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        binding.idRefreshButton.setOnClickListener { refreshWithNewAds() }
        refreshWithNewAds()

    }

    private fun refreshWithNewAds(){

        val builder = AdLoader.Builder(this, "ca-app-pub-3940256099942544/2247696110").forUnifiedNativeAd { unifiedNativeAd ->

            val adView = layoutInflater.inflate(R.layout.activity_main,null) as UnifiedNativeAdView

            populateUnifiedNativeAdView(unifiedNativeAd,adView)

            unifiedNativeAd.adChoicesInfo.apply {
                Log.e("AXE","Image : $images")                  // [com.google.android.gms.internal.ads.zzadx@ad838cc]
                Log.e("AXE","Text : $text")                     // ""
            }

            Log.e("AXE","Advertiser ==> ${unifiedNativeAd.advertiser}")         // Freedomology
            Log.e("AXE","Body ===> ${unifiedNativeAd.body}")                    // We guarantee to match or beat comparable travel booked and purchased on our site.
            Log.e("AXE","Call To action ==> ${unifiedNativeAd.callToAction}")   // open   // this is text on Button
            Log.e("AXE","Extras ==> ${unifiedNativeAd.extras}")                 //   Bundle[EMPTY_PARCEL]
            Log.e("AXE","HeadLine ==> ${unifiedNativeAd.headline}")         // Test Ad : Best Travel Guarantee.
            Log.e("AXE","Icon ==> ${unifiedNativeAd.icon}")                 // null or image
            Log.e("AXE","Images ==> ${unifiedNativeAd.images}")             // [com.google.android.gms.internal.ads.zzadx@9268715]
            Log.e("AXE","isCustomClickGestureEnabled ==> ${unifiedNativeAd.isCustomClickGestureEnabled}")   //true
            Log.e("AXE","isCustomMuteThisAdEnabled ==> ${unifiedNativeAd.isCustomMuteThisAdEnabled}")  // false
            Log.e("AXE","mediaContent ==> ${unifiedNativeAd.mediaContent}")   // com.google.android.gms.internal.ads.zzze@1ce1a2a
            unifiedNativeAd.mediaContent.apply {
                Log.e("AXE","============================================================")
                Log.e("AXE","Aspect Ratio : ${this.aspectRatio}")           // 1.910828
                Log.e("AXE","Current Time : ${this.currentTime}")           // 0.0
                Log.e("AXE","Duration : ${this.duration}")                  // 0.0
                Log.e("AXE","Main Image : ${this.mainImage}")               // android.graphics.drawable.BitmapDrawable@736201b
                Log.e("AXE","Video Controller : ${this.videoController.apply {
                    Log.e("AXE","-----------------------------------------------------------------")
                    Log.e("AXE","${this.isClickToExpandEnabled}")           //  false
                    Log.e("AXE","${this.isCustomControlsEnabled}")          //  false
                    Log.e("AXE","${this.isMuted}")                          //  true
                    Log.e("AXE","${this.isClickToExpandEnabled}")           //  false
                    Log.e("AXE","${this.playbackState}")                    //  0
                    Log.e("AXE","${this.videoCurrentTime}")                 //  0.0
                    Log.e("AXE","${this.play()}")                           //  Kotlin.Unit
                    Log.e("AXE","${this.videoDuration}")                    //  0.0
                    Log.e("AXE","${this.videoLifecycleCallbacks}")          // null
                    Log.e("AXE","${this.hasVideoContent()}")                // false            // unifiedNativeAd.mediaContent.hasVideoContent()
                    Log.e("AXE","-----------------------------------------------------------------")
                }}")
                Log.e("AXE","============================================================")
            }

            Log.e("AXE","muteThisAdReasons ==> ${unifiedNativeAd.muteThisAdReasons}")           // muteThisAdReasons ==> []
            Log.e("AXE","adChoicesInfo ==> ${unifiedNativeAd.adChoicesInfo}")                   // com.google.android.gms.internal.ads.zzadp@f757f91
            Log.e("AXE","Price ==> ${unifiedNativeAd.price}")                                   // null
            Log.e("AXE","Response Info ==> ${unifiedNativeAd.responseInfo}")                    // com.google.android.gms.ads.ResponseInfo@d0baef6
            Log.e("AXE","StartRating ==> ${unifiedNativeAd.starRating}")                        // null
            Log.e("AXE","Store ==> ${unifiedNativeAd.store}")                                   // null

            adView.setNativeAd(unifiedNativeAd)

        }.build()

        builder.loadAd(AdRequest.Builder().build())

    }

    private fun populateUnifiedNativeAdView(nativeAd: UnifiedNativeAd, adView: UnifiedNativeAdView) {

        uniNativeAd = nativeAd

        adView.mediaView = binding.adMedia
        adView.headlineView = binding.adHeadline
        adView.bodyView = binding.adBody
        adView.callToActionView = binding.adCallToAction
        adView.iconView = binding.adAppIcon
        adView.priceView = binding.adPrice
        adView.starRatingView = binding.adStars
        adView.storeView = binding.adStore
        adView.advertiserView = binding.adAdvertiser

        /***
         * check nullability of
         *
         *      nativeAd.body           =========>   (adView.bodyView as TextView).text = nativeAd.body
         *      nativeAd.callToAction   =========>   (adView.callToActionView as Button).text = nativeAd.callToAction
         *      nativeAd.icon           =========>   (adView.iconView as ImageView).setImageDrawable(nativeAd.icon.drawable)
         *      nativeAd.price          =========>   adView.priceView as TextView).text = nativeAd.price
         *      nativeAd.store          =========>   (adView.storeView as TextView).text = nativeAd.store
         *      nativeAd.starRating     =========>   (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
         *      nativeAd.advertiser     =========>   (adView.advertiserView as TextView).text = nativeAd.advertiser
         * */

        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)


        adView.mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
        Log.e("ASP","HeadLine : ${nativeAd.headline}")
        Log.e("ASP","Media Content : ${nativeAd.mediaContent}")

        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            Log.e("ASP","Body : ${nativeAd.body}")
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            Log.e("ASP","TextOnButton : ${nativeAd.callToAction}")
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            Log.e("ASP","Icon Image : ${nativeAd.icon.drawable}")
            (adView.iconView as ImageView).setImageDrawable(nativeAd.icon.drawable)
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            Log.e("ASP","Price : ${nativeAd.price}")
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            Log.e("ASP","Store : ${nativeAd.store}")
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            Log.e("ASP","Rating : ${nativeAd.starRating!!.toFloat()}")
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            Log.e("ASP","Advertiser Name : ${nativeAd.advertiser}")
            adView.advertiserView.visibility = View.VISIBLE
        }

        adView.setNativeAd(nativeAd)

        /**
        val vc = nativeAd.videoController
        if (vc.hasVideoContent()) {
            Log.e("AXE","Video status: Ad contains a ${vc.aspectRatio} video asset.")
            vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    Log.e("AXE","Video status: Video playback has ended.")
                    super.onVideoEnd()
                }
            }
        } else {
            Log.e("AXE","Video status: Ad does not contain a video asset.")
        }
        */
    }

    override fun onDestroy() {
        super.onDestroy()
        uniNativeAd.destroy()
    }
}