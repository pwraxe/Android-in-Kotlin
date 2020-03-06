package com.example.customtoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.customtoast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding?.idButton?.setOnClickListener {

            val toast = Toast(this)
            val inflater = LayoutInflater.from(this).inflate(R.layout.toast,null,false)
            toast.view = inflater
            toast.duration = Toast.LENGTH_LONG
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

    }
}
