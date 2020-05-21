package com.example.datatransferfragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.datatransferfragment.databinding.Fragment2Binding


class Fragment_2 : Fragment() {

    private var binding : Fragment2Binding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_2, container, false)

        //*****************************METHOD-1*************************************
        //val getText = arguments?.getString("uData")

        //*****************************METHOD-2*************************************
        val getText = Fragment_2Args.fromBundle(requireArguments())
        Log.e("AXE", getText.userText)
        binding?.idSetText?.text = getText.userText

        setHasOptionsMenu(true)

        return binding?.root
    }


}
