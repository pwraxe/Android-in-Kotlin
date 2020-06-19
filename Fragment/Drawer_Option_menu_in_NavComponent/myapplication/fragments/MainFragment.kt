package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.CustomRecyclerAdapter
import com.example.myapplication.DataClass
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.random.Random


class MainFragment : Fragment() {

    private var dataList = ArrayList<DataClass>()

    private var binding :FragmentMainBinding? = null

    var isThis = false
    var count = 1

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)
        loadData()

        binding?.idRecyclerView?.adapter = CustomRecyclerAdapter(dataList)

        binding?.idAdd?.setOnClickListener {
            val index = Random.nextInt(10)
            Log.e("AXE","Add Index : $index")
            dataList.add(index, DataClass(R.drawable.ic_add,"item Added : $index"))
            binding?.idRecyclerView?.adapter?.notifyItemChanged(index)
        }

        binding?.idRemove?.setOnClickListener {
            val index = Random.nextInt(10)
            Log.e("AXE","Remove Index : $index")

            dataList.removeAt(index)
            binding?.idRecyclerView?.adapter?.notifyItemRemoved(index)
        }

        return binding?.root
    }

    private fun loadData(){


        repeat(50){
            addList()
        }
    }

    private fun addList(){
        isThis = if(isThis){
            dataList.add(DataClass(R.drawable.ic_doller,"Dollar : $count"))
            false
        } else {
            dataList.add(DataClass(R.drawable.ic_ok, "OK : $count"))
            true
        }
        //dataList.shuffle()
        count++
    }

}