package com.example.materialdesign_part_3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private var context: Context,private var dataList: ArrayList<FaviconName>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    var count = 1

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imageIcon = itemView.findViewById<ImageView>(R.id.id_image)
        val name = itemView.findViewById<TextView>(R.id.id_row_name)
        val addView = itemView.findViewById<ImageView>(R.id.id_add_image)
        val removeView = itemView.findViewById<ImageView>(R.id.id_remove_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design,null,false))

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageIcon?.setImageResource(dataList[position].Icon)
        holder.name?.text = dataList[position].name

        holder.addView?.setImageResource(R.drawable.ic_add)
        holder.removeView?.setImageResource(R.drawable.ic_remove)

        holder.addView?.setOnClickListener {
            dataList.add(position,FaviconName(R.drawable.ic_launcher_background,"Item Insert - $count"))
            notifyItemInserted(position)
            count++
        }
        holder.removeView?.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,dataList.size)
            Toast.makeText(context,"Item removed at : $position",Toast.LENGTH_SHORT).show()
        }


    }
}