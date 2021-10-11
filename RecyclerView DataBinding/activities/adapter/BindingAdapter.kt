package com.example.recyclerviewbindadapter.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recyclerviewbindadapter.R
import com.example.recyclerviewbindadapter.retrofit.MarsPhotos
import com.example.recyclerviewbindadapter.retrofit.NetworkStatus


/***
 * Note: 2nd Parameter always make nullable
 * **/


/*** The value 'marsImage' place at raw_layout.xml file in imageView ***/
@BindingAdapter("marsImage")
fun bindImage(imageView:ImageView,imageUrl : String?){

    imageUrl?.let {
        val imageUri =imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/*** The value 'recyclerList' place at activity_main.xml in RecyclerView Attribute ***/
@BindingAdapter("recyclerList")
fun bindRecyclerView(recyclerView: RecyclerView, mPhotos: List<MarsPhotos>?){
    val adapter =recyclerView.adapter as MarsPhotoRecyclerViewAdapter
    adapter.submitList(mPhotos)
}


/*** The value 'networkStatus' place at ImageView in activity_main.xml ***/
@BindingAdapter("networkStatus")
fun bindNetworkStatus(imageView: ImageView,status: NetworkStatus){

    when(status){
        NetworkStatus.DONE -> { imageView.visibility = View.GONE }

        NetworkStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        NetworkStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }

}