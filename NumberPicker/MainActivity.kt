package com.example.chipsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {


    private var numberPicker : NumberPicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker = findViewById(R.id.id_numPick)
        numberPicker?.maxValue = 100
        numberPicker?.minValue = 0

        numberPicker?.setOnValueChangedListener { picker, oldVal, newVal ->
            Toast.makeText(this," $oldVal : $newVal",Toast.LENGTH_SHORT).show()
        }

    }
}