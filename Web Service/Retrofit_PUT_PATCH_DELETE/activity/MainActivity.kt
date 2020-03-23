package com.example.retrofit_put_patch_delete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.retrofit_put_patch_delete.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null


    private var retrofit : Retrofit? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //putData()

        //------------------------------------------------------------------------------------------

        patchData()

        //------------------------------------------------------------------------------------------

        //deleteData()

        //------------------------------------------------------------------------------------------

        binding?.idRetryButton?.setOnClickListener {
            binding?.idProgress?.visibility = View.VISIBLE
            binding?.idDisplayData?.visibility = View.GONE
            binding?.idErrorMsg?.visibility = View.GONE
            binding?.idRetryButton?.visibility = View.GONE
            //putData()
            patchData()
            //deleteData()

        }

    }

    private fun putData() {

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val online = OnlineData(10,null,"Sample Body")
        val json = retrofit?.create(JsonAPI::class.java)?.putCustomData(3,online)

        json?.enqueue(object : Callback<OnlineData>{
            override fun onFailure(call: Call<OnlineData>, t: Throwable) {
                binding?.idProgress?.visibility = View.GONE
                binding?.idDisplayData?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idRetryButton?.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<OnlineData>, response: Response<OnlineData>) {

                if(response.isSuccessful){
                    val data = response.body()


                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idDisplayData?.visibility = View.VISIBLE
                    binding?.idRetryButton?.visibility = View.GONE


                    binding?.idDisplayData?.text = "\n Code : ${response.code()}" +
                            "\n ID : ${data?.getID()}" +
                            "\n User ID  : ${data?.userId}" +
                            "\n Title : ${data?.title ?: null}" +
                            "\n Body : ${data?.body ?: null}" +
                            "\n ________________________"
                }
            }
        })



    }

    private fun patchData() {

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val online = OnlineData(10,null,"Sample Body")

        val json = retrofit?.create(JsonAPI::class.java)?.patchCustomData(3,online)

        json?.enqueue(object : Callback<OnlineData>{
            override fun onFailure(call: Call<OnlineData>, t: Throwable) {
                binding?.idProgress?.visibility = View.GONE
                binding?.idDisplayData?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idRetryButton?.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<OnlineData>, response: Response<OnlineData>) {

                if(response.isSuccessful){
                    val data = response.body()


                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idDisplayData?.visibility = View.VISIBLE
                    binding?.idRetryButton?.visibility = View.GONE


                    binding?.idDisplayData?.text = "\n Code : ${response.code()}" +
                            "\n ID : ${data?.getID()}" +
                            "\n User ID  : ${data?.userId}" +
                            "\n Title : ${data?.title ?: null}" +
                            "\n Body : ${data?.body ?: null}" +
                            "\n ________________________"
                }
            }
        })


    }

    private fun deleteData() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val json = retrofit?.create(JsonAPI::class.java)?.deleteCustomData(3)
        json?.enqueue(object  : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                binding?.idProgress?.visibility = View.GONE
                binding?.idDisplayData?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idRetryButton?.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if(response.isSuccessful){

                    val data = response.body()   // return type here is --> Void

                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idDisplayData?.visibility = View.VISIBLE
                    binding?.idRetryButton?.visibility = View.GONE

                    binding?.idDisplayData?.text = "\n Code : ${response.code()}"



                }
            }
        })


    }
}
