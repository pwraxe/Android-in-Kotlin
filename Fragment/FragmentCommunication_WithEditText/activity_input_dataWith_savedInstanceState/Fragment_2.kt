package com.example.fragmentpractice

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.*

class Fragment_2 : Fragment() {

    private var fNameSetter : TextView? = null
    private var lNameSetter : TextView? = null

    private var fnameStr : String? = null
    private var lnameStr : String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_2, container, false)
        if(savedInstanceState == null)
        {
            fNameSetter?.text = "Akshay"
            lNameSetter?.text = "Pawar"
        }
        else
        {
            fNameSetter = v?.findViewById(R.id.fnameSetter_id)
            lNameSetter = v?.findViewById(R.id.lnameSetter_id)
            fnameStr = savedInstanceState.getString("f")
            lnameStr = savedInstanceState.getString("l")
            fNameSetter?.text = fnameStr
            lNameSetter?.text = lnameStr

        }
        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fNameSetter = activity?.findViewById(R.id.fnameSetter_id)
        lNameSetter = activity?.findViewById(R.id.lnameSetter_id)

    }
    fun collectData(fname : String?,lname : String?)
    {
        fnameStr = fname
        lnameStr = lname
        fNameSetter?.text = fname
        lNameSetter?.text = lname
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("f",fnameStr)
        outState.putString("l",lnameStr)

    }


}
