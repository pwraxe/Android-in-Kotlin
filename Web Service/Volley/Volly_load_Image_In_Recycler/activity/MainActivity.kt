package com.example.volley_recycler_load_img


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley_recycler_load_img.databinding.ActivityMainBinding
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var singlePost = ArrayList<OnlineData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idProgress?.visibility = View.VISIBLE
        binding?.idRecyclerView?.visibility = View.GONE

        loadPosts()
    }

    private fun loadPosts() {

        val url = "https://pixabay.com/api/?key=15745555-d342d00f85f2ab3998a613c5e&pretty=true"
        val mQueue = Volley.newRequestQueue(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {

                val postArr = it.getJSONArray("hits")
                for (index in 0 until postArr.length()) {

                    val postObj = postArr.getJSONObject(index)

                    singlePost.add(
                        OnlineData(
                            postObj?.getString("largeImageURL"),
                            postObj?.getString("user"),
                            postObj?.getString("userImageURL"),
                            postObj.getInt("views"),
                            postObj.getInt("downloads"),
                            postObj.getInt("favorites"),
                            postObj.getInt("comments")
                        )
                    )
                }

                binding?.idProgress?.visibility = View.GONE
                binding?.idRecyclerView?.visibility = View.VISIBLE
                binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                binding?.idRecyclerView?.adapter = CustomRecyclerAdapt(applicationContext,SinglePosts = singlePost)


            },
            Response.ErrorListener {
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "${it.message}"
            }
        )

        mQueue.add(request)
    }

}
