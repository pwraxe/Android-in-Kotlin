package com.example.volley_recycler_load_video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley_recycler_load_video.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private var onlineVideoData = ArrayList<OnlineVideoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE

        loadOnlineData()

    }

    private fun loadOnlineData() {

        val url = "https://pixabay.com/api/videos/?key=15745555-d342d00f85f2ab3998a613c5e&pretty=true"
        val mQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener {

                val videoDataArray = it.getJSONArray("hits")

                for (index in 0 until videoDataArray.length()){

                    val singleObj = videoDataArray.getJSONObject(index)

                    val v_url = singleObj.getJSONObject("videos").getJSONObject("tiny").getString("url")


                    onlineVideoData.add(
                        OnlineVideoData(
                            singleObj?.getString("userImageURL"),
                            singleObj?.getString("user"),v_url,
                            singleObj.getInt("views"),
                            singleObj.getInt("downloads"),
                            singleObj.getInt("likes"),
                            singleObj.getInt("comments")
                        ))

                }

                binding?.idErrorMsg?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idRecyclerView?.visibility = View.VISIBLE

                binding?.idRecyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
                binding?.idRecyclerView?.adapter = CustomRecyclerAdapt(this,onlineVideoData)






            },
            Response.ErrorListener {
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "${it.message}"
            })


        mQueue.add(request)
    }

}
