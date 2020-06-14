package com.example.retrofit_viewmodel_databinding

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.retrofit_viewmodel_databinding.retrofit.CustomRecyclerView
import com.example.retrofit_viewmodel_databinding.retrofit.UserData
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("loadOnlineImage")
fun bindImage(imageView: ImageView,url : String?){
    url?.let {

        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(uri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("bindToRecycler")
fun bindRecycler(recyclerView: RecyclerView, userData : ArrayList<UserData>?) {
    val adapter = recyclerView.adapter as CustomRecyclerView
    adapter.submitList(userData)
}


@BindingAdapter("loadCircularImage")
fun loadCircularImage(image : CircleImageView, url : String?){
    url?.let {
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context).load(uri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        ).into(image)
    }
}