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

class MainActivity : AppCompatActivity() {

    private var chipGroup : ChipGroup? = null
    private var maleChip : Chip? = null
    private var femaleChip : Chip? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chipGroup = findViewById(R.id.id_chipGroup)
        maleChip = findViewById(R.id.id_maleChip)
        femaleChip = findViewById(R.id.id_FemaleChip)


        chipGroup?.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                maleChip?.id -> {
                    showToast("Male Selected")
                    maleChip?.setTextColor(Color.WHITE)
                    femaleChip?.setTextColor(Color.BLACK)
                }
                femaleChip?.id -> {
                    showToast("Female Selected")
                    femaleChip?.setTextColor(Color.WHITE)
                    maleChip?.setTextColor(Color.BLACK)
                }
            }
        }

    }

    private fun showToast(msg : String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}
