package com.example.dialogfragmentdemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
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

            Toast.makeText(activity,"Yes was Clicked",Toast.LENGTH_LONG).show()
        }
        noButton?.setOnClickListener {
            Toast.makeText(activity,"No was Clicked",Toast.LENGTH_LONG).show()
        }

        return v
    }

}

