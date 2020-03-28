package com.example.popup_window_listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomListAdapter(private var context: Context, private var dataList: Array<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_list_layout,null,false)
        val laptopName = view.findViewById<TextView>(R.id.id_languages)
        laptopName?.text = dataList[position]
        return view
    }

    override fun getItem(position: Int): Any = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = dataList.size


}