package com.example.passdata_frm_1frg2other

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.loginfragment.Communicater
import com.example.passdata_frm_1frg2other.R


class LoginPage : Fragment() {

    private var username : EditText? = null
    private var password : EditText? = null
    private var loginButton  : Button? = null

    var cm : Communicater? = null

    var user : String? = null
    var pass : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        var v = inflater.inflate(R.layout.fragment_login_page, container, false)

        username = v.findViewById(R.id.user_id)
        password = v.findViewById(R.id.password_id)
        loginButton = v.findViewById(R.id.loginButton_id)
        cm = activity as Communicater


        loginButton?.setOnClickListener {

            user = username?.text.toString()
            pass = password?.text.toString()

            when {
                TextUtils.isEmpty(user) -> username?.error = "Username Should not Empty"
                TextUtils.isEmpty(pass) -> password?.error = "Password Should not Empty"
                else -> cm?.shareData(user,pass)
            }
        }

        return v

    }



}