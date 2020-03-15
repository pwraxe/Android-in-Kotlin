package com.example.retrofit_fetchonlineposts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetchonlineposts.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var mUserID = ArrayList<Int>()
    private var mID = ArrayList<Int>()
    private var mTitle = ArrayList<String>()
    private var mBody = ArrayList<String>()

    var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val intermediate = retrofit.create(JsonAPI::class.java).getPosts()
        intermediate.enqueue(object : Callback<ArrayList<OnlineData>>{
            override fun onFailure(call: Call<ArrayList<OnlineData>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Fail : ${t.message}",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ArrayList<OnlineData>>,
                response: Response<ArrayList<OnlineData>>) {

                val onlineData = response.body()
                val buffer = StringBuffer()
                for (data in onlineData!!){

                    mID.add(data.getID())
                    mUserID.add(data.getUserID())
                    mTitle.add(data.getTitle().toString())
                    mBody.add(data.getBody().toString())
                }
                binding?.idProgress?.visibility = View.GONE
                binding?.idRecyclerView?.visibility = View.VISIBLE
                binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                binding?.idRecyclerView?.adapter = CustomRecyclerAdapter(applicationContext,mID,mUserID,mTitle,mBody) }
        })


    }
}
