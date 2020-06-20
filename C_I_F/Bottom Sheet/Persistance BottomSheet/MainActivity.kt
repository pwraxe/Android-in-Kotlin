package com.example.persistancebottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import com.example.persistancebottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

/*
*
* dataBinding
* dependency : material
*
* */




class MainActivity : AppCompatActivity() {

    private var bottomSheetBehavior : BottomSheetBehavior<ScrollView>? = null
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.idSheet)

        binding.idExpand.setOnClickListener {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.idCollapse.setOnClickListener {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }



        (bottomSheetBehavior as BottomSheetBehavior<ScrollView>).addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //binding.idSheetStatus.text = "Slide"
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> { setStatus("Collapsed") }
                    BottomSheetBehavior.STATE_DRAGGING -> { setStatus("Dragged") }
                    BottomSheetBehavior.STATE_EXPANDED -> { setStatus("Expand") }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> { setStatus("Half Expand") }
                    BottomSheetBehavior.STATE_HIDDEN -> { setStatus("Hidden") }
                    BottomSheetBehavior.STATE_SETTLING -> { setStatus("settling")}
                }
            }
        })

    }

    private fun setStatus(status : String){
        binding.idSheetStatus.text = status
    }
}