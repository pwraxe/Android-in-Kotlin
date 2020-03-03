package com.example.fragmentpractice

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class Fragment_1 : Fragment() {

    var cm : Communicater? = null
    var listView : ListView? = null
    var pos : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_1, container, false)
        if(savedInstanceState == null){}
        else{
            cm?.shareData(pos)
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cm = activity as Communicater
        listView = activity?.findViewById(R.id.listView_id)
        val adapter = ArrayAdapter.createFromResource(activity!!.applicationContext,R.array.language,android.R.layout.simple_list_item_1)
        listView?.adapter = adapter

        listView?.setOnItemClickListener { parent, view, position, id ->
            pos = position
            cm?.shareData(position)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("StrPos",pos)
    }
}
