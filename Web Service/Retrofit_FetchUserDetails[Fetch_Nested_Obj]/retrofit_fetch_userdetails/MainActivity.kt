package com.example.retrofit_fetch_userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetch_userdetails.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var userData = ArrayList<UsersData>()
    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE

        loadUsersData()
    }


    private fun loadUsersData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val json = retrofit.create(JsonAPI::class.java).getUsersData()
        json.enqueue(object : Callback<ArrayList<UsersData>>{
            override fun onFailure(call: Call<ArrayList<UsersData>>, t: Throwable) {

                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Error : \n $t"
            }

            override fun onResponse(call: Call<ArrayList<UsersData>>, response: Response<ArrayList<UsersData>> ) {

                if(response.isSuccessful){
                    val usersData = response.body()

                    Log.e("AXE","=====>  ${usersData?.get(0)?.user_address}")

                    for (index in 0 until usersData!!.size){

                        val geoLocation = GeoLocation(usersData[index].user_address?.addr_geo?.geo_loc_lat,
                            usersData[index].user_address?.addr_geo?.geo_loc_lang)

                        val address = Address(usersData[index].user_address?.addr_street,
                            usersData[index].user_address?.addr_suite,
                            usersData[index].user_address?.addr_city,
                            usersData[index].user_address?.addr_zip,geoLocation)



                        val company = Company(usersData[index].user_company?.comp_name,
                            usersData[index].user_company?.comp_catchPharse,
                            usersData[index].user_company?.comp_bs)

                        userData.add( UsersData(
                            usersData[index].user_id,
                            usersData[index].user_name,
                            usersData[index].user_username,
                            usersData[index].user_email,
                            address,
                            usersData[index].user_phone,
                            usersData[index].user_website,
                            company


                        ) )

                    }

                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                    binding?.idRecyclerView?.adapter = CustomRecyclerAdapter(applicationContext,userData)
                }
            }
        })


    }

}
