package com.example.fragnavigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.fragnavigation.R
import com.example.fragnavigation.databinding.Fragment1Binding


class Fragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding1 = DataBindingUtil.inflate<Fragment1Binding>(layoutInflater,R.layout.fragment_1,container,false)

        binding1?.idButton1?.setOnClickListener {
            it.findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }

        return binding1?.root
    }
}
