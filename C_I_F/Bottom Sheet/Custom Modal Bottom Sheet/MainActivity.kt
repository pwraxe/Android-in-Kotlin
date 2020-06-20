package com.example.persistancebottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.persistancebottomsheet.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.idExpand.setOnClickListener {

            val dialog = BottomSheetDialog(this,R.style.BottomSheetDialogTheme)

            val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.fragment_bottom_sheet,
                findViewById(R.id.id_container))

            val starImage = bottomSheetView.findViewById<ImageView>(R.id.star_image)
            val nameText = bottomSheetView.findViewById<TextView>(R.id.name_text)
            val button = bottomSheetView.findViewById<Button>(R.id.showButton)
            val image1 = bottomSheetView.findViewById<ImageView>(R.id.id_image1)
            val image2 = bottomSheetView.findViewById<ImageView>(R.id.id_image2)

            starImage?.setOnClickListener {
                starImage.setImageResource(R.drawable.ic_person)
                binding.idText.text = "Clicked on Star image"
            }

            nameText?.setOnClickListener {
                binding.idText.text = "Clicked on ${nameText.text}"
            }
            button?.setOnClickListener {
                binding.idText.text = "Clicked on Show Button"
                dialog.dismiss()
            }

            image1?.setOnClickListener {
                binding.idText.text = "Clicked on image 2"
            }

            image2?.setOnClickListener {
                binding.idText.text = "Clicked on image 1"
            }

            dialog.setContentView(bottomSheetView)
            dialog.show()



        }

        }


}
