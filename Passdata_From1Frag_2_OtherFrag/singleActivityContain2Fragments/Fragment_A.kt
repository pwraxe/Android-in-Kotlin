package com.example.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.listfragment.Communicator
import com.example.listfragment.R

class Fragment_A : Fragment(), View.OnClickListener
{
    var cm : Communicator? = null

    private var username : String? = null
    private var password : String? = null

    private var userBox : EditText? = null
    private var passBox : EditText? = null

    private var loginButton : Button? = null


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cm = activity as Communicator
        userBox = activity?.findViewById(R.id.username_id)
        passBox = activity?.findViewById(R.id.password_id)
        loginButton = activity?.findViewById(R.id.loginButton_id)
        loginButton?.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        username = userBox?.text.toString()
        password = passBox?.text.toString()
        cm?.respond(username.toString(),password.toString())
    }






}