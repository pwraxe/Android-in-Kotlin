package com.example.customised_listview_cardlayout

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter (private var context: Context,private var mainText : Array<String>,private var subText : Array<String>,private var gallery : Array<Int>) : BaseAdapter()
{



    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.data_layout,null)

        val main = view.findViewById<TextView>(R.id.mainText)
        val sub : TextView = view.findViewById(R.id.subText)
        val image  = view.findViewById<ImageView>(R.id.image_id)


        main.text = mainText[position]
        sub.text  = subText[position]
        image.setImageResource(gallery[position])


        return view
    }

    override fun getItem(position: Int): Any  = mainText[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mainText.size

}