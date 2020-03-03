package com.example.fragmentpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class Fragment_1 : Fragment() {

    var cm : Communicater? = null
    private var submitButton : Button? = null
    private var fName : EditText? = null
    private var lName : EditText? = null

    private var FirstName : String? = null
    private var LastName : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_1, container, false)
        if(savedInstanceState == null) {}
        else{
            FirstName = savedInstanceState.getString("f1")
            LastName = savedInstanceState.getString("l1")
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cm = activity as Communicater
        fName = activity?.findViewById(R.id.fname_id)
        lName = activity?.findViewById(R.id.lname_id)
        submitButton = activity?.findViewById(R.id.SubmitButton_id)

        submitButton?.setOnClickListener {

            FirstName = fName?.text.toString()
            LastName = lName?.text.toString()

            cm?.shareData(FirstName,LastName)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("f1",FirstName)
        outState.putString("l1",LastName)
    }


}