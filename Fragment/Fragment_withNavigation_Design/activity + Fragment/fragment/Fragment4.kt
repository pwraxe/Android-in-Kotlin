package com.example.fragnavigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.fragnavigation.R
import com.example.fragnavigation.databinding.Fragment4Binding

class Fragment4 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding4 = DataBindingUtil.inflate<Fragment4Binding>(layoutInflater,R.layout.fragment_4,container,false)
        return binding4.root

    }
}
