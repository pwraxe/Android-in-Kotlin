package com.example.searchviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    private var search : androidx.appcompat.widget.SearchView? = null
    private var recycler : RecyclerView? = null
    var quote = arrayListOf<String>("selection","deselected","useful","detected","Shameless","friday","bubble","night","beautiful","cool","Song","disaster",
    "Indian","Country","Village","this","that","animation","visual","Android","CPP","Citizen","Python","Kotin","Android Studio","Go Language","JavaScript","Web Technology")
    
    var filteredQuote : ArrayList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        search = findViewById(R.id.id_search)
        recycler = findViewById(R.id.id_recyclerView)

        recycler?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recycler?.adapter = CustomRecycler(this,quote)

        search?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != null) {
                    filteredQuote?.clear()
                    val search = newText.toLowerCase(Locale.ROOT)
                    quote.forEach {
                        if (it.toLowerCase(Locale.ROOT).contains(search)) {
                           filteredQuote?.add(it)
                        }
                    }
                } else{
                    recycler?.adapter?.notifyDataSetChanged()
                }
                recycler?.adapter = CustomRecycler(applicationContext,filteredQuote)
                return true
            }
        })
    }


}
