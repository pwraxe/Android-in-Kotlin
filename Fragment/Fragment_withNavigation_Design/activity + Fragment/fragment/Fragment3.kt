package com.example.fragnavigation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.fragnavigation.R
import com.example.fragnavigation.databinding.Fragment3Binding


class Fragment3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding3 = DataBindingUtil.inflate<Fragment3Binding>(layoutInflater,R.layout.fragment_3,container,false)
        return binding3?.root
    }

}
