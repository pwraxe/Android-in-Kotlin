package com.example.navcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navcomponent.databinding.FragmentDataBinding

class DataFragment : Fragment() {

    private lateinit var binding : FragmentDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDataBinding.inflate(inflater,container,false)

        binding.idButtonSubmit.setOnClickListener {
            getInput()
        }
        return binding.root
    }

    private fun getInput() {

        val name = binding.idInputName.text.toString()
        var mobile: Long = 0


        if(name.isEmpty()){
            binding.idInputName.error = "Name Required"
            return
        }

        try {
            mobile = binding.idInputNumber.text.toString().toLong()
        }catch (ex: NumberFormatException){
            binding.idInputNumber.error = "Mobile No Required"
            return
        }


        val action = DataFragmentDirections.actionDataFragmentToDisplayFragment(name,mobile)

        findNavController().navigate(action)
    }


}