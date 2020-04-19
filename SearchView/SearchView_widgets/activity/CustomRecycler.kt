package com.example.searchviewdemo

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CustomRecycler(private var context: Context,private var quotes : ArrayList<String>?) : RecyclerView.Adapter<CustomRecycler.ViewHolder>()
{

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var mText = itemView.findViewById<TextView>(R.id.id_sampleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))


    override fun getItemCount(): Int = quotes!!.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mText?.text  = quotes?.get(position) ?: "def"
    }

/*
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredItems = ArrayList<String>()
                if(constraint != null && constraint.isEmpty()){
                    filteredItems.addAll(quotes!!)
                }else{
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (item in quotes!!){
                        if(item.toLowerCase(Locale.ROOT).contains(filterPattern))
                        {
                            filteredItems.add(item)
                        }
                    }
                }
                val result = FilterResults()
                result.values = filteredItems
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {


            }
        }
    }

 */

}
