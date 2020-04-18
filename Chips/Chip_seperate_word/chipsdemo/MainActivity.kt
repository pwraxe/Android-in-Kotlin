package com.example.chipsdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private var et_line : EditText? = null
    private var btn_showChips : Button? = null
    private var chipGroup : ChipGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_line = findViewById(R.id.id_line)
        btn_showChips = findViewById(R.id.id_showChips)
        chipGroup = findViewById(R.id.id_chipGroup)

        btn_showChips?.setOnClickListener {
            val line = et_line?.text.toString().trim()
            if(TextUtils.isEmpty(line)){
                et_line?.error = "Please Enter Line or Sentence"
                et_line?.requestFocus()
                return@setOnClickListener
            }

            val split = line.split(" ")
            for (i in split.indices){
                val chip = Chip(this)
                chip.text = split[i]
                chip.setChipDrawable(ChipDrawable.createFromAttributes(this,null,0,R.style.Widget_MaterialComponents_Chip_Action))
                chipGroup?.addView(chip)
            }
            et_line?.text?.clear()
        }
    }
}