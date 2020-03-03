package com.example.alldemo

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{
    var seekBar : SeekBar? = null
    var numberText : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekbar_id)
        numberText = findViewById(R.id.number_id)

        seekBar?.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numberText?.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                numberText?.text = "onStart : ${seekBar?.progress.toString()}"
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                numberText?.text = "onStop : ${seekBar?.progress.toString()}"
            }
        })
    }


}
