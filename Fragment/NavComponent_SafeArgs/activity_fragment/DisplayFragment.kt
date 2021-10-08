package com.example.navcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.navcomponent.databinding.FragmentDisplayBinding


class DisplayFragment : Fragment() {

    private lateinit var binding: FragmentDisplayBinding

    private val args: DisplayFragmentArgs by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDisplayBinding.inflate(inflater,container,false)

        setData()

        return binding.root
    }

    private fun setData() {
        val name = args.name
        val mobile = args.mobile

        binding.idTextName.text = name.toString()
        binding.idTextMobileNo.text = mobile.toString()
    }


}