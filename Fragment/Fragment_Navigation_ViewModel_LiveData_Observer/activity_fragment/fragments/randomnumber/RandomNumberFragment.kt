package randomnumber.example.safeargsfrag.fragments.randomnumber

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.safeargsfrag.R
import com.example.safeargsfrag.databinding.FragmentRandomNumberBinding

class RandomNumberFragment : Fragment() {

    private lateinit var binding : FragmentRandomNumberBinding
    private lateinit var viewModel : RandomViewModel
    private var randNum : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_random_number, container, false)
        viewModel = ViewModelProvider(this).get(RandomViewModel::class.java)

        viewModel.getRandomNumber.observe(viewLifecycleOwner, Observer { newRandomNumber ->
          binding.idCurrentNumber.text = newRandomNumber.toString()
        })

        binding.nextNumberButton.setOnClickListener { getSetNextNumber() }
        binding.idEndGameButton.setOnClickListener { gameOver() }

        return binding.root
    }

    private fun gameOver(){

        Toast.makeText(context,"Game Over",Toast.LENGTH_LONG).show()


        val action = RandomNumberFragmentDirections.actionRandomNumberFragmentToScoreFragment()
        action.randomNumber = viewModel.getRandomNumber.value!!
        NavHostFragment.findNavController(this).navigate(action)
    }



    private fun getSetNextNumber() = viewModel.getSetNextNumber()


}