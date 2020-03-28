package com.example.popup_window_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.Toast
import com.example.popup_window_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var dataList = arrayOf("Android","Kotlin","PHP","Flutter","Dart","XML","JSON")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idPopupListButton?.setOnClickListener {

            // if this statement not write , then multiple popup window appear on the top and we need to dismiss popup window on each selection
            // and if we write this line then, remove all chances to multiple popup appear and finally we enable this button when user select item in list
            binding?.idPopupListButton?.isEnabled = false
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.def_list_layout,null,false)
            val listView = view.findViewById<ListView>(R.id.id_listView)
            listView?.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)
            listView?.setOnItemClickListener { parent, _, position, _ ->
                showToast(parent?.getItemAtPosition(position).toString())
                window.dismiss()
                binding?.idPopupListButton?.isEnabled = true
            }
            window.contentView = view
            window.showAsDropDown(binding?.idPopupListButton)
            window.update()
        }
        binding?.idPopupCustomListButton?.setOnClickListener {

            binding?.idPopupCustomListButton?.isEnabled = false
            val window = PopupWindow(this)
            val view = LayoutInflater.from(this).inflate(R.layout.def_list_layout,null,false)
            val listView = view.findViewById<ListView>(R.id.id_listView)
            listView?.adapter = CustomListAdapter(this, dataList = dataList)
            listView?.setOnItemClickListener { parent, view, position, id ->
                showToast(parent?.getItemAtPosition(position).toString())
                window.dismiss()
                binding?.idPopupCustomListButton?.isEnabled = true
            }

            window.contentView = view
            window.showAsDropDown(binding?.idPopupCustomListButton!!)
            window.update()



        }
    }

    private fun showToast(msg : String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}
