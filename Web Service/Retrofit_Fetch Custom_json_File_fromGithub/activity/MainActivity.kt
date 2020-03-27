package com.example.retrofit_fetch_custom_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetch_custom_data.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var myOnlineData = ArrayList<MyOnlineData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE

        loadMyOnlineData()
    }

    private fun loadMyOnlineData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/akshay100796/myCustomUsersData/master/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

        val json = retrofit.create(JsonAPI::class.java).getMyOwnData()

        json.enqueue(object : Callback<ArrayList<MyOnlineData>>{
            override fun onFailure(call: Call<ArrayList<MyOnlineData>>, t: Throwable) {

                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Error : $t"
            }

            override fun onResponse(call: Call<ArrayList<MyOnlineData>>, response: Response<ArrayList<MyOnlineData>>) {

                if(response.isSuccessful){
                    val myData = response.body()

                    for (index in 0 until myData!!.size){
                        Log.e("AXE","---> ${myData[0]._email}")

                        myOnlineData.add( MyOnlineData( myData[index]._name,myData[index]._age,myData[index]._email,myData[index]._mobile )  )
                    }

                    binding?.idProgress?.visibility = View.GONE
                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                    binding?.idRecyclerView?.adapter = CustomRecyclerAdapt(applicationContext,myOnlineData)
                }

            }
        } )




    }

}
