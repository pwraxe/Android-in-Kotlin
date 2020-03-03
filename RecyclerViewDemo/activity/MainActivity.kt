package com.example.recycler_view_demo

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{
    var recyclerView : RecyclerView? = null
    var names = arrayOf("Android","Cake","Cloud","Education","Flight","Laptop","Light","Mobile","Moon","Paint","Watch")
    var gallery = arrayOf(R.drawable.android,R.drawable.cake,
        R.drawable.cloud,R.drawable.education,R.drawable.flight,
        R.drawable.laptop,R.drawable.light,R.drawable.mobile,
        R.drawable.moon,R.drawable.paint,R.drawable.watch)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycleView_id)
        recyclerView?.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)


        val recycleAdapter = RecycleAdapter(this,names,gallery)
        recyclerView?.adapter = recycleAdapter

    }
}

class RecycleAdapter(var context: Context,var picName : Array<String>,var gallery : Array<Int>) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = gallery.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder._image.setImageResource(gallery[position])
        holder._text.text = picName[position]

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        var _image = itemView.findViewById<ImageView>(R.id.sample_image_id)
        var _text = itemView.findViewById<TextView>(R.id.sample_text_id)
    }
}