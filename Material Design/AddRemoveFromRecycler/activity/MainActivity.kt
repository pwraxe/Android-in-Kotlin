package com.example.materialdesign_part_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null

    private var DataList = ArrayList<FaviconName>()
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.id_recyclerView)


        repeat(20){
            DataList.add(FaviconName(R.drawable.circular_favicon,"CodexDroid - $count"))
            count++
        }



        recyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)





        recyclerView?.adapter = CustomRecyclerAdapter(this,DataList)
    }
}
class FaviconName(var Icon : Int, var name : String? )