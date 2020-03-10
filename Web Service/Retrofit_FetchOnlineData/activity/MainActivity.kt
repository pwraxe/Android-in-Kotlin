package com.example.retrofit_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit_practice.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit?.create(ApiInterface::class.java)?.getPosts()
        api?.enqueue(object : Callback<List<OnlinePosts>>{
            override fun onFailure(call: Call<List<OnlinePosts>>, t: Throwable) {
                Toast.makeText(applicationContext,"Fail Case due to  : ${t.message}",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<OnlinePosts>>,
                response: Response<List<OnlinePosts>>
            ) {

                var dataList = ""
                if(response.isSuccessful){
                    var posts = response?.body()
                    for(data in posts!!){
                        dataList += "\n" +
                                "ID : ${data?.getId()} \n" +
                                "User ID : ${data.getUserId()} \n" +
                                "Title : ${data.getTitle()} \n" +
                                "Body : ${data.getBody()} \n" +
                                "--------------------------------------------\n"
                    }
                }

                binding?.idOnlineText?.text = dataList

            }
        })

    }
}
