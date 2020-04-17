package com.example.chipsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private var input : EditText? = null
    private var button : Button?  = null
    private var chipGroup : ChipGroup?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.id_input)
        button = findViewById(R.id.id_button)
        chipGroup = findViewById(R.id.id_chipGroup)

        button?.setOnClickListener {

            val data = input?.text?.toString()?.trim()

            if(TextUtils.isEmpty(data)){
                input?.error = "Please Enter Course"
                input?.requestFocus()
                return@setOnClickListener
            }
            val chip = Chip(this)
            chip.text = data
            chip.setChipDrawable(ChipDrawable.createFromAttributes(this,null,0,R.style.Widget_MaterialComponents_Chip_Entry))   //remove button appear becoz of entry

            //chip.setChipIconResource(R.drawable.ic_check_circle)

            chip.isSelected = false  // selected chip display when true, || unselected chip display while false
            chip.isChecked = false   // display checked chip while true, ||  display checked chip while false

            chip.setOnCloseIconClickListener {
                chipGroup?.removeView(it)
            }
            chipGroup?.addView(chip)
            input?.text?.clear()


        }

    }
}
