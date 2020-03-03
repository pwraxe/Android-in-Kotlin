package com.example.flexible_ui_interface

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class Fragment_1 : Fragment() {

    var cm : Communicator? = null
    var listView : ListView? = null
    var str : String? = null
    var textSet : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_1,container,false)
        if(savedInstanceState == null){}
        else{
            textSet = v.findViewById(R.id.textSetter_id)
            textSet?.text = str
        }


        listView = v.findViewById(R.id.listView_id)
        cm = activity as Communicator
        listView?.adapter = ArrayAdapter.createFromResource(activity!!.applicationContext,R.array.language,android.R.layout.simple_list_item_1)
        listView?.setOnItemClickListener { parent, view, position, id ->

            var desc = resources.getStringArray(R.array.description)
            str = desc[position].toString()
            cm?.sendData(str.toString())
        }
        return v
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saveLine",str)
    }


}