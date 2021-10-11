package com.example.recyclerviewbindadapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbindadapter.databinding.RawLayoutBinding
import com.example.recyclerviewbindadapter.retrofit.MarsPhotos

class MarsPhotoRecyclerViewAdapter : ListAdapter<MarsPhotos,MarsPhotoRecyclerViewAdapter.RecyclerViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<MarsPhotos>() {
        override fun areItemsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean = oldItem.img_src == newItem.img_src
    }

    class RecyclerViewHolder (private var binding : RawLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(imageView: MarsPhotos?){
            binding.marsPic = imageView
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RawLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}