package com.example.fragmentpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Fragment_2 : Fragment() {


    var descText : TextView? = null
    var linePos : Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_2, container, false)
        if(savedInstanceState == null){}
        else
        {
            val desc = resources.getStringArray(R.array.description)
            linePos = savedInstanceState.getInt("linePos")
            descText = v?.findViewById(R.id.desc_id)
            descText?.text = desc[linePos]
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        descText = activity?.findViewById(R.id.desc_id)
    }
    fun recievedData(i : Int)
    {
        val desc = resources.getStringArray(R.array.description)
        linePos = i
        descText?.text = desc[i]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("linePos",linePos)
    }
}