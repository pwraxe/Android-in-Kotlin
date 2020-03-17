package com.example.retrofit_fetchdata_with_url

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetchdata_with_url.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var USER_ID = ArrayList<Int>()
    private var ID = ArrayList<Int>()
    private var TITLE = ArrayList<String>()
    private var BODY = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val jsonapi = retrofit.create(JsonAPI::class.java).getPosts("posts")

        jsonapi.enqueue(object : Callback<ArrayList<OnlinePosts>>{
            override fun onFailure(call: Call<ArrayList<OnlinePosts>>, t: Throwable) {
                binding?.idProgress?.visibility = View.GONE
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Fail : $t"
            }

            override fun onResponse(call: Call<ArrayList<OnlinePosts>>, response: Response<ArrayList<OnlinePosts>>) {

                if(response.isSuccessful){

                    var onlineData = response.body()
                    for(data in onlineData!!){
                        USER_ID.add(data.getUserID())
                        ID.add(data.getId())
                        TITLE.add(data.getTitle().toString())
                        BODY.add(data.getBody().toString())
                    }

                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idProgress?.visibility= View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,
                        RecyclerView.VERTICAL,false)

                    binding?.idRecyclerView?.adapter = CustomRecycler(applicationContext,USER_ID,ID,TITLE,BODY)


                }

            }
        })


    }
}
