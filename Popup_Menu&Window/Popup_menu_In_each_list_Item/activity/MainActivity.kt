package com.example.popup_menu_in_listitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popup_menu_in_listitem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private var names : ArrayList<String> = arrayListOf("Mr. Arjun Reddy","Mr. Roshan Kulkutty","Mrs. Vidya Lalvani",
    "Mr. Sahil Zaa","Mr. Vishal Kapoor","Mrs. Shital Kakkad","Mrs. Piyush Arya","Mrs. Somya Basu","Mr. Salman Khan","Mrs. Kareena Kapoor",
    "Mr. Akshay Kumar","Mr. Shahrukh Khan","Mr. Ameer Khan")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idRecyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding?.idRecyclerView?.adapter = CustomRecycler(this,names, binding?.root)

    }

}
