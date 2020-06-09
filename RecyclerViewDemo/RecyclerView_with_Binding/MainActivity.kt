package com.example.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.databinding.ActivityMainBinding
import com.example.recyclerviewdemo.databinding.CustomLayoutBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding?.idRecyclerView?.adapter = CustomRecyclerView()

    }
}

class CustomRecyclerView  : RecyclerView.Adapter<CustomRecyclerView.ViewHolder>(){

    class ViewHolder(var binding: CustomLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomLayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = 20

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.idImageView.setImageResource(android.R.drawable.ic_menu_delete)

        holder.binding.idRowText.text = "Sample Raw Text"

        holder.binding.idGoodMoodImage.setImageResource(R.drawable.ic_good_mood)

    }
}
