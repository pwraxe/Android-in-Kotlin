package com.example.databinding_hi_low_game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.databinding_hi_low_game.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    private val args :ResultFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_result, container, false)

        val score = args.score
        binding.idFinalScore.text = "Your Score : $score"

        return binding.root
    }


}