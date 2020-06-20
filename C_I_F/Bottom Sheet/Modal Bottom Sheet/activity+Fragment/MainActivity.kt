package com.example.persistancebottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.persistancebottomsheet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding : ActivityMainBinding
    private lateinit var bottomSheet : BottomSheetFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        bottomSheet = BottomSheetFragment()
        binding.idExpand.setOnClickListener {

            bottomSheet.show(supportFragmentManager,"anyTag")

        }
    }

    override fun showActionToTextView(str: String) {

        binding.idText.text = str

    }
}
