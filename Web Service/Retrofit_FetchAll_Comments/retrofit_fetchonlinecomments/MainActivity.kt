package com.example.retrofit_fetchonlinecomments

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_fetchonlinecomments.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var comm_postId = ArrayList<Int>()
    private var comm_id = ArrayList<Int>()
    private var comm_name = ArrayList<String>()
    private var comm_email = ArrayList<String>()
    private var comm_body = ArrayList<String>()

    private var binding: ActivityMainBinding? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idProgress?.visibility = View.VISIBLE
        binding?.idRecyclerView?.visibility = View.GONE

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val intermediate = retrofit.create(JsonAPI::class.java).getComment()

        intermediate.enqueue(object : Callback<ArrayList<OnlineComments>> {
            override fun onFailure(call: Call<ArrayList<OnlineComments>>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail with : ${t.message}", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(
                call: Call<ArrayList<OnlineComments>>,
                response: Response<ArrayList<OnlineComments>>
            ) {

                if (response.isSuccessful) {
                    val onlineComment = response.body()
                    for (data in onlineComment!!) {

                        comm_id.add(data.getID())
                        comm_postId.add(data.getPostID())
                        comm_name.add(data.getName().toString())
                        comm_email.add(data.getEmail().toString())
                        comm_body.add(data.getBody().toString())
                    }

                    binding?.idProgress?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE
                    binding?.idRecyclerView?.layoutManager =
                        LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                    binding?.idRecyclerView?.adapter = CustomRecyclerAdapter(
                        applicationContext, com_id = comm_id, com_post_id = comm_postId,
                        com_name = comm_name, com_email = comm_email, com_body = comm_body
                    )
                }
            }
        })
    }
}
