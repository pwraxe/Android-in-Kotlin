package com.example.onlineimageslider.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.onlineimageslider.R
import com.example.onlineimageslider.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        viewModelFactory = HomeViewModelFactory(requireContext())

        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)


        viewModel.listOfImages.observe(viewLifecycleOwner, Observer {
            binding.idViewPager.adapter = OnlineImageAdapter(it)
        })


        return binding.root

    }


}