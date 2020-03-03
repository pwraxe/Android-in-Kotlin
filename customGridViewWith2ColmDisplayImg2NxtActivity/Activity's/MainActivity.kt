package com.example.gridviewdemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity()
{
    private var gridView : GridView? = null

    private val mainText = arrayOf("Education","Mobile","Cloud","Laptop","Watch",
        "Paint","Light","Flight","cake","Moon")

    private val gallery = arrayOf(
        R.drawable.education,R.drawable.mobile,
        R.drawable.cloud,R.drawable.laptop,
        R.drawable.watch,R.drawable.paint,
        R.drawable.light,R.drawable.flight,
        R.drawable.cake,R.drawable.moon

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.gridView_id)
        val myGridAdapter = MyGridAdapter(this,gallery,mainText)

        gridView!!.adapter = myGridAdapter

        gridView!!.setOnItemClickListener { parent, _, position, _ ->

            val gridText : String = parent.getItemAtPosition(position).toString()

            val intent = Intent(this,ViewFullData::class.java)
            intent.putExtra("pic",gallery[position])
            intent.putExtra("mainText",gridText)
            startActivity(intent)



        }


    }
}




class MyGridAdapter(private val context: Context,private var gallery: Array<Int>,private var mainText : Array<String>) : BaseAdapter()
{
    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.data_layout,null)

        val img = view.findViewById<ImageView>(R.id.image_id)
        val main = view.findViewById<TextView>(R.id.mainText)


        img.setImageResource(gallery[position])
        main.text = mainText[position]
        return view
    }

    override fun getItem(position: Int): Any = mainText[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mainText.size

}