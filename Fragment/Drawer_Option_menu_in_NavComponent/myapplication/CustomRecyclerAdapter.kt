package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_design.view.*

class CustomRecyclerAdapter(private var dataList : ArrayList<DataClass>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var image = itemView.id_image
        var mainText = itemView.id_mainText

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_design,parent,false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(dataList[position].imageView)
        holder.mainText.text = dataList[position].mainText

    }


}