package com.example.custom_expandable_listview

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.custom_expandable_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var sgo = ArrayList<SingleGroupObject>()

    private var expandAdapter : ExpandableListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.idExpandListView?.visibility = View.GONE
        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idExpandListView?.visibility = View.VISIBLE

        loadData()
    }

    private fun loadData() {

        val mQueue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET,"https://pixabay.com/api/?key=15745555-d342d00f85f2ab3998a613c5e&pretty=true",null,
            Response.Listener {

                val dataArray = it.getJSONArray("hits")
                for (index in 0 until dataArray.length()){

                    val singleObj = dataArray.getJSONObject(index)
                    sgo.add(
                        SingleGroupObject(
                            singleObj?.getString("userImageURL"),
                            singleObj?.getString("user"),
                            singleObj.getInt("views"),
                            singleObj.getInt("likes"),
                            singleObj.getInt("comments"),
                            singleObj.getInt("downloads")
                        )
                    )
                }

                binding?.idErrorMsg?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idExpandListView?.visibility = View.VISIBLE

                expandAdapter = CustomExpandListAdapter(this,sgo)
                binding?.idExpandListView?.setAdapter(expandAdapter)

                binding?.idExpandListView?.setOnGroupClickListener { parent, v, groupPosition, id ->

                    if(parent?.isGroupExpanded(groupPosition)!!){
                        parent.collapseGroup(groupPosition)
                    }else{
                        parent.expandGroup(groupPosition)
                    }
                    return@setOnGroupClickListener true
                }
            },
            Response.ErrorListener {
                binding?.idExpandListView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Error : \n $it"
            })

         mQueue.add(request)
    }

}