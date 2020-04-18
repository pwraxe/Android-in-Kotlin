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

    private var actionChip1 : Chip? = null
    private var actionChip2 : Chip? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionChip1 = findViewById(R.id.id_toast)
        actionChip2 = findViewById(R.id.id_snack)

        actionChip1?.setOnClickListener {
            Toast.makeText(this,"Action Chip 1 with icon and Default style",Toast.LENGTH_SHORT).show()
        }

        actionChip2?.setOnClickListener {
            Snackbar.make(it,"Action Chip 2 with red background and icon | auto close snack",Snackbar.LENGTH_LONG).show()
        }

    }
}