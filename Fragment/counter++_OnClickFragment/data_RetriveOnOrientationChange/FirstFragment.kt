package com.example.fragment_part_insingleactivity


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    var cm : Communicater? = null
    var clickButton : Button? = null
    var counter : Int = 0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_first,container,false)
        if(savedInstanceState == null){}
        else{
            counter = savedInstanceState.getInt("counter")
        }
        Log.e("Axe First Fragment","onCreateView()")

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clickButton = view?.findViewById(R.id.clickButton)
        cm = activity as Communicater
        clickButton?.setOnClickListener {
            counter++
            cm?.respond("Button Click @ $counter times")
            Log.e("Axe First Fragment","onActivityCreated()")

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter",counter)
        Log.e("Axe First Fragment","onSaveInstanceState")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("Axe First Fragment","onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("Axe First Fragment","onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.e("Axe First Fragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("Axe First Fragment","onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Axe First Fragment","onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e("Axe First Fragment","onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Axe First Fragment","onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Axe First Fragment","onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("Axe First Fragment","onDetach()")
    }

}