package com.example.flexible_ui_interface

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Fragment_2 : Fragment() {

    var setDesc : TextView? = null
    var string : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_2, container, false)
        setDesc = v.findViewById(R.id.textSetter_id)

        return v
    }

    fun recivedData(str : String)
    {
        string = str
        setDesc?.text = str
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("saveLines",string)
    }
}