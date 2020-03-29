package com.example.expandablelistviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var expandListView : ExpandableListView? = null
    private var _singleObject = ArrayList<HeaderChildData>()

    private var adapter : ExpandableListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandListView = findViewById(R.id.id_expandedList)

        _singleObject.add(HeaderChildData("Laptop's", arrayListOf("Dell","Apple","HP","Asus","Lenovo","Samsung")))
        _singleObject.add(HeaderChildData("Languages", arrayListOf("Android","Kotlin","Python","JavaScript","Java","Ruby on Rails")))
        _singleObject.add(HeaderChildData("Family", arrayListOf("Father","Mother","Brother","Sister","Grand Father","Grand Mother","Uncle","Aunty","Cousin")))

        adapter = CustomExpandListAdapter(this,_singleObject)
        expandListView?.setAdapter(adapter)

        expandListView?.setOnGroupClickListener { parent, v, groupPosition, id ->
            title = _singleObject[groupPosition]._header
            if(expandListView?.isGroupExpanded(groupPosition)!!){
                expandListView?.collapseGroup(groupPosition)
            }else{
                expandListView?.expandGroup(groupPosition)
            }

            return@setOnGroupClickListener true
        }


        expandListView?.setOnChildClickListener{ parent, v, groupPosition, childPosition, id ->
            Toast.makeText(this,"${_singleObject[groupPosition]._childs[childPosition]}",Toast.LENGTH_LONG).show()

            return@setOnChildClickListener true
        }
        expandListView?.setOnGroupExpandListener {
            Toast.makeText(this,"expand",Toast.LENGTH_SHORT).show()
        }

        expandListView?.setOnGroupCollapseListener {
            Toast.makeText(this,"collapse",Toast.LENGTH_SHORT).show()
        }
    }
}
