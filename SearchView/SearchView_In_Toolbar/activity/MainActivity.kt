package com.example.searchviewdemo

import android.app.SearchManager
import android.app.SearchableInfo
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    var words = arrayListOf<String>("selection","deselected","useful","detected","Shameless","friday","bubble","night","beautiful","cool","Song","disaster",
    "Indian","Country","Village","this","that","animation","visual","Android","CPP","Citizen","Python","Kotin","Android Studio","Go Language","JavaScript","Web Technology")

    private var filteredItems = ArrayList<String>()

    private var recyclerView : RecyclerView? = null
    private var toolbar : Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.id_recycler)
        toolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(toolbar)

        recyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView?.adapter = CustomRecyclerView(this,words)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_search,menu)

        val searchItem = menu?.findItem(R.id.id_ic_search)
        val searchView = searchItem?.actionView as SearchView


        searchItem.expandActionView()
        searchView.queryHint = "Find here..."


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {

                if(newText != null){
                    filteredItems.clear()
                    val input = newText.toString().toLowerCase(Locale.ROOT)
                    words.forEach {
                        if(it.contains(newText.toString())){
                            filteredItems.add(it)
                        }
                    }
                }else {
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
                recyclerView?.adapter = CustomRecyclerView(applicationContext,filteredItems)
                return true
            }
        })

        return true
    }
}