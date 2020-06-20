package com.example.persistancebottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.persistancebottomsheet.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentBottomSheetBinding
    private lateinit var communicator: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_sheet, container, false)

        communicator = activity as Communicator
        binding.idImage1.setOnClickListener { communicator.showActionToTextView("Second Image were Clicked")
            this.dismiss()
        }
        binding.idImage2.setOnClickListener { communicator.showActionToTextView("First Image were Clicked") }
        binding.nameText.setOnClickListener { communicator.showActionToTextView(it.name_text.text as String) }
        binding.showButton.setOnClickListener { communicator.showActionToTextView("ShowButton was Clicked")
            this.dismiss()
        }
        binding.starImage.setOnClickListener {
            binding.starImage.setImageResource(R.drawable.ic_person)
            communicator.showActionToTextView("Star Image were clicked")
        }

        return binding.root
    }
}

interface  Communicator{

    fun showActionToTextView(str : String)

}