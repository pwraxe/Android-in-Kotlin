package com.example.retrofit_getpostswithquery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_getpostswithquery.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var USER_ID = ArrayList<Int>()
    private var ID = ArrayList<Int>()
    private var TITLE = ArrayList<String>()
    private var BODY = ArrayList<String>()

    var binding : ActivityMainBinding?  = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonAPI = retrofit.create(JsonApi::class.java).getPosts(intArrayOf(3,5,9))

        jsonAPI.enqueue(object : Callback<ArrayList<OnlinePosts>>{
            override fun onFailure(call: Call<ArrayList<OnlinePosts>>, t: Throwable) {
                Toast.makeText(applicationContext,"Fail : ${t.message}",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ArrayList<OnlinePosts>>, response: Response<ArrayList<OnlinePosts>>) {

                if(response.isSuccessful){
                    val onlineData = response.body()
                    for(data in onlineData!!){
                        USER_ID.add(data.getUserId())
                        ID.add(data.getId())
                        TITLE.add(data.getTitle().toString())
                        BODY.add(data.getBody().toString())
                    }
                    Log.e("AXE","$USER_ID")
                    if(USER_ID.isEmpty() || ID.isEmpty() || TITLE.isEmpty() || BODY.isEmpty()){
                        binding?.idRecycler?.visibility = View.GONE
                        binding?.idProgress?.visibility = View.GONE
                        binding?.idEmptyDataMsg?.visibility = View.VISIBLE
                    }else {
                        binding?.idProgress?.visibility = View.GONE
                        binding?.idRecycler?.visibility = View.VISIBLE
                        binding?.idRecycler?.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                        binding?.idRecycler?.adapter = CustomRecyclerAdapter(applicationContext, USER_ID, ID, TITLE, BODY)
                    }
                }
            }
        })
    }
}
