package com.example.viewbindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.example.viewbindingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idText?.setOnClickListener {
            binding.idText.text = "Seen"
        }
        binding?.idImg?.setOnClickListener {
            binding.idImg.setImageResource(R.mipmap.ic_launcher_round)
        }

        binding?.idButton?.setOnClickListener {
            Toast.makeText(this@MainActivity,"Button Clicked",Toast.LENGTH_LONG).show()
        }

        binding?.idCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Toast.makeText(this@MainActivity,"Checked",Toast.LENGTH_LONG).show()
                binding.idCheckBox.text = "Checked"
            }else{
                Toast.makeText(this@MainActivity,"unChecked",Toast.LENGTH_LONG).show()
                binding.idCheckBox.text = "Unchecked"
            }
        }

        binding?.idRadioGrp?.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.id_radioMale -> Toast.makeText(this@MainActivity,"Male",Toast.LENGTH_LONG).show()
                R.id.id_radioFemale -> Toast.makeText(this@MainActivity,"Female",Toast.LENGTH_LONG).show()
            }
        }

        binding?.idRating?.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this@MainActivity,"Rating : $rating",Toast.LENGTH_LONG).show()
        }

        binding?.idSeekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Toast.makeText(this@MainActivity,"$progress",Toast.LENGTH_LONG).show()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding?.idSwitch?.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this@MainActivity,"$isChecked",Toast.LENGTH_LONG).show()
            binding.idSwitch.text = isChecked.toString()
        }

    }
}
