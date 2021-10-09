package com.example.databinding_hi_low_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.databinding_hi_low_game.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding.idButtonHigh.setOnClickListener {
            homeViewModel.playGame("+")
        }

        binding.idButtonLow.setOnClickListener {
            homeViewModel.playGame("-")
        }

        homeViewModel.randomNo.observe(viewLifecycleOwner,{randomNo ->
            binding.idNumber.text = randomNo.toString()
        })

        homeViewModel.score.observe(viewLifecycleOwner,{score ->
            binding.idScore.text = "Score : ${homeViewModel.score.value}"
        })

        homeViewModel.currentAttempted.observe(viewLifecycleOwner,{ attempt ->
            binding.idAttempt.text = "Attempted : ${homeViewModel.currentAttempted.value} / 10"
            if(homeViewModel.currentAttempted.value == 10) gotoNextScreen()
        })

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        homeViewModel.resetData()
    }

    private fun gotoNextScreen(){
        val action = HomeFragmentDirections.actionHomeFragment3ToResultFragment2(homeViewModel.score.value!!)
        findNavController().navigate(action)
    }
}