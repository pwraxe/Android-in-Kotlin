package com.example.fragnavigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.fragnavigation.R
import com.example.fragnavigation.databinding.Fragment2Binding

class Fragment2 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding2 = DataBindingUtil.inflate<Fragment2Binding>(layoutInflater,R.layout.fragment_2,container,false)

        binding2?.button2?.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }

        binding2?.button3?.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment2_to_fragment4)
        }

        return binding2?.root
    }

}
