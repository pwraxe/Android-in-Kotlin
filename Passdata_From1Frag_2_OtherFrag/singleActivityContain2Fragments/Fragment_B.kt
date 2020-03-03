package com.example.listfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.listfragment.R

class Fragment_B : Fragment() {

    private var username : TextView? = null
    private var password : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        username =  activity?.findViewById(R.id.rowUsername_id) as TextView
        password = activity?.findViewById(R.id.rowPassword_id) as TextView

    }

    fun collectData(username : String,password : String)
    {
        this.username?.text = username
        this.password?.text = password
    }

}