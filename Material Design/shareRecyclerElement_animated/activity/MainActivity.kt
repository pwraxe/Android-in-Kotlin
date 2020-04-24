package com.example.timepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var progress : ProgressBar? = null
    private var errorMsg : TextView? = null

    private var uniPost = ArrayList<OnlineData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Share Element"




        recyclerView = findViewById(R.id.id_recyclerView)
        progress = findViewById(R.id.id_progress)
        errorMsg = findViewById(R.id.id_errorMsg)

        progress?.visibility = View.VISIBLE
        recyclerView?.visibility = View.GONE
        errorMsg?.visibility =View.GONE

        loadData()
    }

    private fun loadData() {
        val url = "https://pixabay.com/api/?key=15745555-d342d00f85f2ab3998a613c5e&q=yellow+flowers&image_type=photo&pretty=true"
        val mQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {

            val dataArray = it.getJSONArray("hits")
            for (index in 0 until dataArray.length())
            {
                val singleObj = dataArray.getJSONObject(index)
                uniPost.add(OnlineData(singleObj?.getString("largeImageURL"),

                    singleObj?.getInt("views"),
                    singleObj?.getInt("downloads"),
                    singleObj?.getInt("favorites"),
                    singleObj?.getInt("likes"),
                    singleObj?.getInt("comments"),
                    singleObj?.getString("user"),
                    singleObj?.getString("userImageURL")
                    ))
            }

            progress?.visibility = View.GONE
            errorMsg?.visibility =View.GONE
            recyclerView?.visibility = View.VISIBLE

            recyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            recyclerView?.adapter  =CustomAdapter(this,uniPost)

        }, Response.ErrorListener {
            progress?.visibility = View.GONE
            recyclerView?.visibility = View.GONE
            errorMsg?.visibility =View.VISIBLE
            errorMsg?.text = "Error : ${it.message}"
        })

        mQueue?.add(request)

    }
}
