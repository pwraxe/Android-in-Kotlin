package randomnumber.example.safeargsfrag.fragments.score

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.safeargsfrag.R
import com.example.safeargsfrag.databinding.FragmentScoreBinding

class ScoreFragment() : Fragment() {

    private lateinit var binding : FragmentScoreBinding

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score, container, false)

        val data = ScoreFragmentArgs.fromBundle(requireArguments()).randomNumber
        Log.e("AXE","DATA : $data")
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).randomNumber)
        viewModel =ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)
        binding.idScoreNumber.text = viewModel.score.value.toString()


        return binding.root
    }

}