package com.example.volly_fetch_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonObject


class MainActivity : AppCompatActivity() {

    private var disText : TextView? = null
    private var button : Button? = null
    private var progress : ProgressBar? = null

    var queue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)

        disText = findViewById(R.id.id_displayMsg)
        button = findViewById(R.id.id_button)
        progress = findViewById(R.id.id_progress)

        button?.setOnClickListener {
            progress?.visibility  = View.VISIBLE
            disText?.visibility = View.GONE
            parseData()
        }
    }

    private fun parseData() {

        val url = "https://api.myjson.com/bins/kp9wz"
        val request = JsonObjectRequest(Request.Method.GET,url, null,
            Response.Listener {

                progress?.visibility = View.GONE
                disText?.visibility = View.VISIBLE
                val jsonArr = it.getJSONArray("employees")

                val add = StringBuilder()
                for (i in 0 until jsonArr.length()){
                    val emp = jsonArr.getJSONObject(i)

                    add.append("${emp?.getString("firstname")} | ${emp?.getInt("age")} | ${emp?.getString("mail")} \n\n")
                }
                disText?.text = add

            },
            Response.ErrorListener {
                disText?.text = "Error : ${it.message}"
                Log.e("AXE","---------------> ${it.message}")
            }
            )

        queue?.add(request)

    }
}
