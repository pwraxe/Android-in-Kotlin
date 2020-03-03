package com.example.fragment_part_insingleactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    var rowText : TextView? = null
    var str : String? =  null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_second,container,false)
        if(savedInstanceState == null){}
        else{
            str = savedInstanceState.getString("rowText")
            var txt = view.findViewById<TextView>(R.id.sample_text_id)
            txt?.text  = str
        }
        Log.e("Axe Second Fragment","__onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rowText = view?.findViewById(R.id.sample_text_id)
        Log.e("Axe Second Fragment","__onActivityCreated")

    }

    fun getData(s : String) {
        str = s
        rowText?.text = s
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("rowText", str)
        Log.e("Axe Second Fragment","__onSaveInstanceState")
    }


    override fun onStart() {
        super.onStart()
        Log.e("Axe Second Fragment","__onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Axe Second Fragment","__onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Axe Second Fragment","__onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Axe Second Fragment","__onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Axe Second Fragment","__onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Axe Second Fragment","__onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Axe Second Fragment","__onDetach")
    }
}