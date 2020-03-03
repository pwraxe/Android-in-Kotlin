package com.example.alldemo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{

    private var spin : Spinner? = null
    private var mainText : TextView? = null
    private var language = arrayOf("C","C++","Java","PHP","Python","Javascript","Kotlin","Android OS",
        "HTML","CSS","iPhone OS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainText = findViewById(R.id.mainText_id)
        spin = findViewById(R.id.custom_spinner_id)
        val customAdapter = CustomSpinner(this,language)
        spin?.adapter = customAdapter

        spin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int,id: Long) {
                mainText?.text = language[position]
            }

        }
    }


}
class CustomSpinner(private var context : Context,private var language : Array<String>) : BaseAdapter() {
    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.data_layout,null)

        val mainRowText = view.findViewById<TextView>(R.id.rowText_id)
        mainRowText.text = language[position]
        return view
    }
    override fun getItem(position: Int): Any = language[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = language.size
}