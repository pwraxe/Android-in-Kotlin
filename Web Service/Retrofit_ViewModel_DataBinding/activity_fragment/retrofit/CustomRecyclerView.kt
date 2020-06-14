package com.example.retrofit_viewmodel_databinding.retrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_viewmodel_databinding.databinding.RawRecycleDesignBinding
import com.example.retrofit_viewmodel_databinding.homefragment.HomeFragmentDirections


class CustomRecyclerView : ListAdapter<UserData,CustomRecyclerView.ViewHolder>(DiffCallBack()) {


    class ViewHolder(var binding : RawRecycleDesignBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(userData: UserData){
            binding.homeFrag = userData
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RawRecycleDesignBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = getItem(position)
        holder.bind(userData)
        Log.e("AXE","Recycler bind ====================>  : ${userData.views}")
        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context,"You tapped on ${userData.user}",Toast.LENGTH_LONG).show()
            Navigation.findNavController(holder.itemView).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                userData.userImageURL.toString(),
                userData.user.toString(),
                userData.views,
                userData.largeImageURL.toString(),
                userData.likes,
                userData.comments,
                userData.favorites,
                userData.downloads))

        }

    }
}

class DiffCallBack : DiffUtil.ItemCallback<UserData>() {
    override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean = oldItem === newItem
    override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean = oldItem.user == newItem.user
}