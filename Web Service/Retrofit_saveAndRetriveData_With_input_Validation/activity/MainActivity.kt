package com.example.retrofit_savedata_method2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_savedata_method2.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException
import java.security.PrivateKey

class MainActivity : AppCompatActivity() {


    // Don't forget INTERNET-PERMISSION in manifest

    private var binding : ActivityMainBinding? = null

    private var mID : Int = 0
    private var mUserID : Int = 0
    private var mTitle : String? = null
    private var mBody : String? = null

    private val sadEmojiFace = 0x1F614


    private var retrofit : Retrofit? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idButton?.setOnClickListener {

            try {
                mID = binding?.idMId?.text.toString().toInt()
            }catch (npe : NumberFormatException){
                binding?.idMId?.error = "ID required"
                binding?.idMId?.requestFocus()
                return@setOnClickListener
            }

            try{
                mUserID = binding?.idMUserId?.text.toString().trim().toInt()
            }catch (npe : NumberFormatException){
                binding?.idMUserId?.error = "User ID Required"
                binding?.idMUserId?.requestFocus()
                return@setOnClickListener
            }

            mTitle = binding?.idMTitle?.text?.toString()?.trim()
            mBody = binding?.idMBody?.text.toString().trim()

            if(isEmpty(mTitle)){
                binding?.idMTitle?.error = "Title Required"
                binding?.idMTitle?.requestFocus()
                return@setOnClickListener
            }
            if(isEmpty(mBody)){
                binding?.idMBody?.error = "Body Required"
                binding?.idMBody?.requestFocus()
                return@setOnClickListener
            }
            saveData(mID,mUserID,mTitle,mBody)
        }
        binding?.idRetry?.setOnClickListener {

            binding?.idErrorMsg?.visibility = View.GONE
            binding?.idRecyclerView?.visibility = View.GONE
            binding?.idProgress?.visibility = View.GONE
            binding?.idRetry?.visibility = View.GONE
            binding?.idLinear?.visibility = View.VISIBLE
        }
    }

    private fun saveData(id : Int, userId : Int, title : String?,body : String?) {


        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idLinear?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE

        val save = SaveDataOnline(id,userId,title,body)

        val json = retrofit?.create(JsonAPI::class.java)?.saveData(save)
        json?.enqueue(object : Callback<SaveDataOnline>{
            override fun onFailure(call: Call<SaveDataOnline>, t: Throwable) {
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idLinear?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "We are Facing Poor Internet Connection ${getEmoji(sadEmojiFace)}"
                binding?.idRetry?.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<SaveDataOnline>, response: Response<SaveDataOnline>) {
                if(response.isSuccessful) {
                    binding?.idLinear?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    val onlineData = response.body()
                    // taking id from user is completely west now becoz
                    // we are storing data online ,
                    // in json_placeholder website there are 100 id already ,
                    // now you are putting your data in there database  temporary hence default generated id is 101
                    // hence you can see those 100 id's at https://jsonplaceholder.typicode.com/posts

                    mID = onlineData?.getId()!!
                    mUserID = onlineData.getUserId()
                    mTitle = onlineData.getTitle()
                    mBody = onlineData.getBody()

                    Log.e("AXE","$mID : $mUserID : $mTitle : $mBody")

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,RecyclerView.VERTICAL,false)
                    binding?.idRecyclerView?.adapter = CustomRecycler(applicationContext,mID,mUserID,mTitle,mBody)
                }



            }
        })

    }

    fun getEmoji(unicode: Int): String {
        return String(Character.toChars(unicode))
    }
}
