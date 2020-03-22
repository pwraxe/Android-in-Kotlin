package com.example.retrofit_savedata_method3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_savedata_method3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    private var _id : Int = 0
    private var _userId : Int = 0
    private var _title : String? = null
    private var _body : String? = null

    private var retrofit : Retrofit? = null

    private var sadFace : Int = 0x2639

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.idSendButton?.setOnClickListener {

            try {
                _id = binding?.idId?.text.toString().toInt()
                binding?.idId?.setBackgroundResource(R.drawable.back_border)
            } catch (nfe: NumberFormatException) {
                binding?.idId?.error = "ID Required"
                binding?.idId?.requestFocus()
                binding?.idId?.setBackgroundResource(R.drawable.error_border)
                return@setOnClickListener
            }

            try {
                _userId = binding?.idUserId?.text.toString().toInt()
                binding?.idUserId?.setBackgroundResource(R.drawable.back_border)
            } catch (nfe: NumberFormatException) {
                binding?.idUserId?.error = "user ID Required"
                binding?.idUserId?.requestFocus()
                binding?.idUserId?.setBackgroundResource(R.drawable.error_border)
                return@setOnClickListener
            }

            _title = binding?.idTitle?.text.toString()
            _body = binding?.idBody?.text.toString()

            if (TextUtils.isEmpty(_title)) {
                binding?.idTitle?.error = "Title Required"
                binding?.idTitle?.requestFocus()
                binding?.idTitle?.setBackgroundResource(R.drawable.error_border)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(_body)) {
                binding?.idBody?.error = "Message Body Required"
                binding?.idBody?.requestFocus()
                binding?.idBody?.setBackgroundResource(R.drawable.error_border)
                return@setOnClickListener
            }


            saveData(_id, _userId, _title, _body)

        }
    }

    private fun saveData(id : Int,userId : Int,title : String?, body : String?){

        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding?.idSendButton?.visibility = View.GONE
        binding?.idErrorMsg?.visibility = View.GONE
        binding?.idRecyclerView?.visibility = View.GONE
        binding?.idProgress?.visibility = View.VISIBLE
        binding?.idLinearData?.visibility = View.VISIBLE


        // Method 1 to save online data---------------------------------Method 1
        //val saveIt = SaveOnlineData(id,userId,title,body)
        //val json = retrofit?.create(JsonAPI::class.java)?.saveDataOnPosts(saveIt)


        // method 2 to save online Data---------------------------------Method 2
        // val json = retrofit?.create(JsonAPI::class.java)?.saveDataOnPosts(id,userId,title,body)

        //Method 3 to Save online Data ---------------------------------Method 3
        val map = HashMap<String,String>()
        map["id"] = id.toString()
        map["userId"] = userId.toString()
        map["title"] = title as String
        map["body"] = body as String

        val json = retrofit?.create(JsonAPI::class.java)?.saveDataOnPosts(map)
        

        json?.enqueue(object : Callback<SaveOnlineData>{
            override fun onFailure(call: Call<SaveOnlineData>, t: Throwable) {
                binding?.idSendButton?.visibility = View.GONE
                binding?.idRecyclerView?.visibility = View.GONE
                binding?.idProgress?.visibility = View.GONE
                binding?.idLinearData?.visibility = View.GONE
                binding?.idErrorMsg?.visibility = View.VISIBLE
                binding?.idErrorMsg?.text = "${getEmoji(sadFace)} We are facing, \n poor Internet Connection !"
            }

            override fun onResponse(call: Call<SaveOnlineData>,response: Response<SaveOnlineData>) {

                if(response.isSuccessful){

                    val data = response.body()
                    _id = data?.getId()!!
                    _userId = data.getUserId()
                    _title = data.getTitle()
                    _body =data.getBody()

                    binding?.idSendButton?.visibility = View.GONE
                    binding?.idProgress?.visibility = View.GONE
                    binding?.idLinearData?.visibility = View.GONE
                    binding?.idErrorMsg?.visibility = View.GONE
                    binding?.idRecyclerView?.visibility = View.VISIBLE

                    binding?.idRecyclerView?.layoutManager = LinearLayoutManager(applicationContext,
                    RecyclerView.VERTICAL,false)
                    binding?.idRecyclerView?.adapter = CustomRecycler(applicationContext,_id,_userId,_title,_body)
                }
            }
        })
    }
    private fun getEmoji(code : Int) : String{
        return String(Character.toChars(code))
    }
}
