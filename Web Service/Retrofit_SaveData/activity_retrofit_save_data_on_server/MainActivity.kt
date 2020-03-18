package com.example.retrofit_save_data_on_server

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_save_data_on_server.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private var binding : ActivityMainBinding? = null

    private var ID = ArrayList<Int>()
    private var USER_ID = ArrayList<Int>()
    private var TITLE = ArrayList<String>()
    private var BODY = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        fetchData()
    }

    private fun fetchData() {
        val saveData = SaveAsPost(1234,1526,"Sample Title","Sample Body")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val json = retrofit.create(JsonAPI::class.java).saveData(saveData)

        json.enqueue(object : Callback<SaveAsPost>{
            override fun onFailure(call: Call<SaveAsPost>, t: Throwable) {
                binding?.idRecycler?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "Something went wrong!"
            }

            override fun onResponse(call: Call<SaveAsPost>, response: Response<SaveAsPost>) {

                if(response.isSuccessful) {
                    val onlineData = response.body()

                    onlineData?.getId()?.let { ID.add(it) }
                    onlineData?.getUserId()?.let { USER_ID.add(it) }
                    onlineData?.getTitle()?.let { TITLE.add(it) }
                    onlineData?.getBody()?.let { BODY.add(it) }

                    binding?.idRecycler?.visibility = View.VISIBLE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idErrorMsg?.visibility = View.GONE

                    binding?.idRecycler?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                    binding?.idRecycler?.adapter = CustomRecycler(applicationContext,ID,USER_ID,TITLE,BODY)
                }
            }
        })
    }
}
