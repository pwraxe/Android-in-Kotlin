package com.example.dialogfragmentdemo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogFrag : DialogFragment() {

    private var yesButton : Button? = null
    private var noButton : Button? = null
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dialog,null)

        yesButton = v.findViewById(R.id.yesButton_id)
        noButton = v.findViewById(R.id.noButton_id)

        yesButton?.setOnClickListener {
            Toast.makeText(activity?.applicationContext,"Yes Button Click",Toast.LENGTH_LONG).show()
            dismiss()
        }
        noButton?.setOnClickListener {
            Toast.makeText(activity?.applicationContext,"No Button Click",Toast.LENGTH_LONG).show()
            dismiss()
        }



        return v
    }
}
