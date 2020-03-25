package com.example.volley_fetch_json_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.volley_fetch_json_data.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idLoadButton?.setOnClickListener {

            binding?.idDisplayText?.visibility = View.GONE
            binding?.idProgress?.visibility = View.VISIBLE

            loadData()

        }
    }

    private fun loadData() {

        val mQueue = VolleySingleton.getInstance(this)?.requestQueue

        val request = JsonObjectRequest(Request.Method.GET,"https://api.myjson.com/bins/18k3wk",null,
            Response.Listener {

                binding?.idProgress?.visibility = View.GONE
                binding?.idDisplayText?.visibility = View.VISIBLE
                val add = StringBuilder()

                val jsonArr =it.getJSONArray("employees")
                for(data in 0 until jsonArr.length()){
                    val emp = jsonArr.getJSONObject(data)
                    add.append("${emp.getString("firstname")} > ${emp.getInt("age")} > ${emp?.getString("mail")}\n\n")
                }
                binding?.idDisplayText?.text = add


            },
            Response.ErrorListener {

                binding?.idProgress?.visibility = View.GONE
                binding?.idDisplayText?.text = "Error : \n $it"
            }
            )

        mQueue?.add(request)

    }
}
