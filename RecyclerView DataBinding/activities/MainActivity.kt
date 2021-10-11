package com.example.recyclerviewbindadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewbindadapter.adapter.MarsPhotoRecyclerViewAdapter
import com.example.recyclerviewbindadapter.databinding.ActivityMainBinding
import com.example.recyclerviewbindadapter.retrofit.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.lifecycleOwner = this@MainActivity

        binding.mainViewModel = viewModel

        binding.idRecyclerView.adapter = MarsPhotoRecyclerViewAdapter()

    }
}