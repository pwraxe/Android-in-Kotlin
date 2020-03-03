package com.example.simplecustomspinner

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var simple : Spinner? = null
    private var custom : Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  gadgets = arrayOf("Android","Cake","Cloud","Education","Flight","Laptop","Light","Mobile","Moon","Paint","Watch")
        val gallery = arrayOf(R.drawable.android,R.drawable.cake,R.drawable.cloud,R.drawable.education,
            R.drawable.flight,R.drawable.laptop,R.drawable.light,R.drawable.mobile,
            R.drawable.moon,R.drawable.paint,R.drawable.watch)

        simple = findViewById(R.id.simpleSpinner_id)
        custom = findViewById(R.id.customSpinner_id)

        val simpleAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,gadgets)
        simple?.adapter = simpleAdapter

        // ------------------------------------------------------>  Custom Spinner

        val customAdapter = MyCustomAdapter(this,gadgets,gallery)
        custom?.adapter = customAdapter
    }
}

class MyCustomAdapter(private val context: Context,private val gadgets : Array<String>,private val gallery: Array<Int>) : BaseAdapter()
{
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.data_layout,null)

        val image = view.findViewById<ImageView>(R.id.row_image)
        val txt = view.findViewById<TextView>(R.id.row_text)

        image.setImageResource(gallery[position])
        txt.text = gadgets[position]
        return view

    }

    override fun getItem(position: Int): Any = gallery[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = gallery.size

}