package com.example.retrofit_fetch_photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetch_photo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private var picArray = ArrayList<OnlinePhotos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE

        loadImages()
    }

    private fun loadImages() { 

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val json = retrofit.create(JsonAPI::class.java).getImages()

        json.enqueue(object : Callback<ArrayList<OnlinePhotos>>{
            override fun onFailure(call: Call<ArrayList<OnlinePhotos>>, t: Throwable) {
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Error : \n ${t.message}"
            }

            override fun onResponse(call: Call<ArrayList<OnlinePhotos>>, response: Response<ArrayList<OnlinePhotos>>) {

                if(response.isSuccessful){

                    val photoData = response.body()
                    for (data in photoData!!){
                        picArray.add( OnlinePhotos(data._id,data._album_id,data._title,data._url_1,data._url_2 ))


                    }

                    binding?.idProgress?.visibility = View.GONE
                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                    binding?.idRecyclerView?.adapter = CustomRecycler(applicationContext,picArray)

                }
            }
        })



    }

}
