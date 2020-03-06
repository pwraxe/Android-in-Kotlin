package com.example.customlist

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.custom_list.*
import kotlinx.android.synthetic.main.zoom_image.*

class MainActivity : AppCompatActivity() {

    private var list : ListView? = null

    private val lang = arrayOf("C","C++","Java","Kotlin","Python","JavaScript")
    private val images = arrayOf(R.mipmap.wall1,R.mipmap.wall2,R.mipmap.wall3,R.mipmap.wall4,
        R.mipmap.wall5,R.mipmap.wall6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.id_listView)
        list?.adapter = CustomAdapter(this,lang,images)

    }

}

class CustomAdapter(var context: Context,var lang : Array<String>,var wallpapers : Array<Int>) : BaseAdapter(){
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_list,null,false)

        val textView = view.findViewById<TextView>(R.id.id_text)
        textView?.text = lang[position]
        val pics = view.findViewById<ImageView>(R.id.id_imageView)
        pics.setImageResource(wallpapers[position])

        pics?.setOnClickListener {

            val inf = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val v =inf.inflate(R.layout.zoom_image,null,false)

            val d = Dialog (context)
            d.setContentView(v)
            d.id_zoom_img?.setImageResource(wallpapers[position])
            d.show()
        }

        textView?.setOnClickListener {
            Toast.makeText(context, lang[position],Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = lang.size

}
