package com.example.expandablelistview_practice2

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private var expandableList : ExpandableListView? = null
    private var expandableAdapter : ExpandableListAdapter? = null
    private var sendObject = ArrayList<ParentChildGroup>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandableList = findViewById(R.id.id_expandableList)

        sendObject.add(ParentChildGroup(R.drawable.ic_laptop, arrayListOf("HP","Dell","Apple","Lenovo","Samsung","Acer","Asus","Sony")))
        sendObject.add(ParentChildGroup(R.drawable.ic_android, arrayListOf("CupCake","Donut","Eclair","Froyo","Gingerbread","Honey-Comb",
            "Ice-CreamSandwich","JellyBean","Kitkat","Lollipop","Marshmallow","Nougat","Oreo","Pie","--Q--","--R--")))
        sendObject.add(ParentChildGroup(R.drawable.ic_flite, arrayListOf("Air India","Vistara","Air India Express","GoAir","IndiGo","SpiceJet",
        "AirAsia India","Zoom Air")))
        expandableAdapter = CustomExpandableAdapter(this,sendObject)
        expandableList?.setAdapter(expandableAdapter)
        expandableList?.setGroupIndicator(resources.getDrawable(R.drawable.group_indiacator))


        expandableList?.setOnGroupClickListener { parent, v, groupPosition, id ->
            if(expandableList?.isGroupExpanded(groupPosition)!!) {
                expandableList?.collapseGroup(groupPosition)
            } else {
                expandableList?.expandGroup(groupPosition)

            }
            return@setOnGroupClickListener true
        }
        expandableList?.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(this,"${sendObject[groupPosition]._childList[childPosition]}",Toast.LENGTH_LONG).show()
            return@setOnChildClickListener true
        }


    }
}
