package com.example.materialdesignpart2

import android.app.ActivityOptions
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley



class MainActivity : AppCompatActivity() {


    private var url = "https://pixabay.com/api/?key=15745555-d342d00f85f2ab3998a613c5e&q=yellow+flowers&image_type=photo&pretty=true"

    private var recycler : RecyclerView? = null
    private var progress : ProgressBar? = null
    private var errorMsg : TextView? = null

    private var onlineData = ArrayList<OnlineData>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progress = findViewById(R.id.id_progress)
        recycler = findViewById(R.id.id_recycler)
        errorMsg = findViewById(R.id.id_error_msg)

        recycler?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        recycler?.visibility = View.GONE
        errorMsg?.visibility = View.GONE
        progress?.visibility = View.VISIBLE


        loadData()

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun loadData() {

        val mQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {

            val data = it.getJSONArray("hits")
            for (index in 0 until data.length()){
                val singleObj = data.getJSONObject(index)
                onlineData.add(
                    OnlineData(
                        singleObj?.getString("largeImageURL"),
                        singleObj?.getString("user"),
                        singleObj?.getInt("views"),
                        singleObj?.getInt("downloads"),
                        singleObj?.getInt("likes"),
                        singleObj?.getInt("comments")
                    )
                )
            }

            progress?.visibility = View.GONE
            errorMsg?.visibility = View.GONE
            recycler?.visibility = View.VISIBLE

            recycler?.adapter = CustomRecyclerAdapter(window,applicationContext,onlineData)


        },Response.ErrorListener {

            recycler?.visibility = View.GONE
            progress?.visibility = View.GONE
            errorMsg?.visibility = View.VISIBLE
            errorMsg?.text = "${it.message}"
        })


        mQueue.add(request)

    }

}
