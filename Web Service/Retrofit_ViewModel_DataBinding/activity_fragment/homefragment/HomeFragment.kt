package com.example.retrofit_viewmodel_databinding.homefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.retrofit_viewmodel_databinding.R
import com.example.retrofit_viewmodel_databinding.databinding.FragmentHomeBinding
import com.example.retrofit_viewmodel_databinding.retrofit.CustomRecyclerView


class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null
    private var homeViewModel : HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val homeViewModelFactory = HomeViewModelFactory(requireNotNull(this.activity).application)
        homeViewModel = ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)

        binding?.homeFrag = homeViewModel
        binding?.lifecycleOwner = this

        binding?.idRecyclerView?.adapter = CustomRecyclerView()


        return binding?.root
    }

}