package com.example.passdata_frm_1frg2other


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class UserData : Fragment() {

    var userText : TextView? = null
    var passText : TextView? = null

    var userN : String? = null
    var passW : String? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        val v = inflater.inflate(R.layout.fragment_user_data, container, false)
        userText = v?.findViewById(R.id.usernameSetter_id)
        passText = v?.findViewById(R.id.PasswordSetter_id)

        userN =  arguments?.getString("U")
        passW = arguments?.getString("P")

        userText?.text = userN.toString()
        passText?.text = passW.toString()

        return v
    }

}