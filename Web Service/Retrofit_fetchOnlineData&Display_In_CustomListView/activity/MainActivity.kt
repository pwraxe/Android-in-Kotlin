package com.example.retrofit_xtractdata

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.retrofit_xtractdata.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding?= null
    private var idList = ArrayList<String>()
    private var userIdList = ArrayList<String>()
    private var titleList = ArrayList<String>()
    private var bodyList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val intermediate = retrofit.create(JsonAPI::class.java).getPosts()

        intermediate.enqueue(object : Callback<ArrayList<OnlineJsonData>>{
            override fun onFailure(call: Call<ArrayList<OnlineJsonData>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Fail on ${t.message}",Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<ArrayList<OnlineJsonData>>, response: Response<ArrayList<OnlineJsonData>>) {
                if(response.isSuccessful){

                    val onlineData = response.body()
                    for (data in onlineData!!) {
                        idList.add("${data.getID()}")
                        userIdList.add("${data.getUserID()}")
                        titleList.add("${data.getTitle()}")
                        bodyList.add("${data.getBody()}")
                    }
                    binding?.idList?.adapter = CustomList(this@MainActivity,idList,userIdList,titleList,bodyList)
                    binding?.idList?.setOnItemClickListener { parent, _, position, _ ->
                        Toast.makeText(this@MainActivity,"${parent?.getItemAtPosition(position)}",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@MainActivity,"Something went wrong on Response",Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}

class CustomList(var context: Context,var idList : ArrayList<String>,var userIdList : ArrayList<String>,var titleList : ArrayList<String>,var bodyList :ArrayList<String>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_layout,null,false)

        val idText = view.findViewById<TextView>(R.id.id_text_id)
        val userIdText = view.findViewById<TextView>(R.id.id_user_id)
        val titleText = view.findViewById<TextView>(R.id.id_title)
        val bodyText = view.findViewById<TextView>(R.id.id_body)

        idText?.text = "ID : ${idList[position]}"
        userIdText?.text = "User ID : ${userIdList[position]}"
        titleText?.text = "Title : ${titleList[position]}"
        bodyText?.text = "Body : ${bodyList[position]}"

        return view


    }

    override fun getItem(position: Int): Any = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = idList.size

}
