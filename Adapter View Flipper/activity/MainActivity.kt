package com.example.appbarlayoutdemo

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private var adapterViewFlipper : AdapterViewFlipper? = null
    private var icons = arrayListOf(R.drawable.cupcake,R.drawable.ic_check,R.drawable.eclair,R.drawable.ic_close,
    R.drawable.gingerbread,R.drawable.ic_contact_mail,R.drawable.icecreamsandwich,R.drawable.ic_person,R.drawable.kitkat,
    R.drawable.lollipop,R.drawable.marshmallow,R.drawable.nougat,R.drawable.oreo,R.drawable.pie)

    private var verisonName = arrayListOf("CupCake","Check Icon","Eclair","close icon",
    "GingerBread","Contact Mail Icon","Ice-Creme Sandwich","Contact Person icon","Kitkat",
        "Lollipop","marshmallow","Nougat","Oreo","Pie")

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Adapter View Flipper"

        adapterViewFlipper = findViewById(R.id.id_adapterViewFlipper)
        adapterViewFlipper?.flipInterval = 2000
        adapterViewFlipper?.isAutoStart = true

        adapterViewFlipper?.adapter = CustomAdapterFlipper(this,icons,verisonName)

    }
}

class CustomAdapterFlipper(private var context: Context,
    private var icons : ArrayList<Int>,
    private var versionName : ArrayList<String>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_design,parent,false)
        view?.findViewById<ImageView>(R.id.id_versionIcon)
            ?.setImageResource(icons[position])
        view?.findViewById<TextView>(R.id.id_versionName)
            ?.text = versionName[position]
        return view
    }

    override fun getItem(position: Int): Any = icons[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = icons.size

}