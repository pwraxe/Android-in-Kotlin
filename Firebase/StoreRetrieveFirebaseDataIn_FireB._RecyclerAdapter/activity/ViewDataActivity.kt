package com.example.store_retrieved_data_firebase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.store_retrieved_data_firebase.databinding.ActivityViewDataBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import com.google.firebase.database.core.Repo

class ViewDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewDataBinding

    private lateinit var fireReference : DatabaseReference

    private var onlineUsersData = ArrayList<UsersCommonData>()

    private var adapter : CustomRecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_data)

        fireReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.idProgressBar.visibility = View.VISIBLE

        Toast.makeText(this,"Waiting for load data",Toast.LENGTH_LONG).show()
        loadData()

        binding.idProgressBar.visibility = View.GONE
        val option = FirebaseRecyclerOptions.Builder<UsersCommonData>()
            .setQuery(FirebaseDatabase.getInstance().getReference("Users"),UsersCommonData::class.java)
            .build()

        adapter =  CustomRecyclerAdapter(option)
        binding.idRecyclerView.adapter = adapter

    }

    private fun loadData() {

        synchronized(this){

            binding.idProgressBar.visibility = View.VISIBLE
            // val usersData : UsersCommonData

            fireReference.addValueEventListener(object : ValueEventListener{

                override fun onCancelled(error: DatabaseError) {
                    binding.idErrorMsg.text = error.toString()
                    binding.idProgressBar.visibility = View.GONE
                }

                @SuppressLint("RestrictedApi")
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (data in snapshot.children){

                        val key     : String?           = data.key
                        val count   : Long              = data.childrenCount
                        val priority: Any?              = data.priority
                        val db_ref  : DatabaseReference = data.ref
                        val fdb     : FirebaseDatabase  = data.ref.database
                        val parent  : DatabaseReference?= data.ref.parent
                        val root    : DatabaseReference = data.ref.root
                        val repo    : Repo              = data.ref.repo
                        val value   : String            = data.value.toString()
                        val exists  : Boolean           = data.exists()

                        Log.e("AXE","Key : $key")               //   Key : 123456789
                        Log.e("AXE","count : $count")           //   count : 4
                        Log.e("AXE","priority : $priority")     //   priority : null
                        Log.e("AXE","db_ref : $db_ref")         //   db_ref : https://store-retrieved-data-firebase.firebaseio.com/Users/123456789
                        Log.e("AXE","fireB DB : $fdb")          //   fireB DB : com.google.firebase.database.FirebaseDatabase@9d5d724
                        Log.e("AXE","parent : $parent")         //   parent : https://store-retrieved-data-firebase.firebaseio.com/Users
                        Log.e("AXE","root : $root")             //   root : https://store-retrieved-data-firebase.firebaseio.com
                        Log.e("AXE","repo : $repo")             //   repo : https://store-retrieved-data-firebase.firebaseio.com
                        Log.e("AXE","value : $value")           //   value : {usersAddData={area=my area, country=my country, town=my town, city=my city, street=my street, state=my state}, mobile=123456789, name=alex jonhnson, email=alexjoe@gmail.com}
                        Log.e("AXE","exist : $exists")          //   exist : true

                        //val usersData : UsersCommonData? = data.getValue(UsersCommonData::class.java)
                        //onlineUsersData.add(data.getValue(UsersCommonData::class.java)!!) // OR     onlineUsersData.add(usersData)
                    }


                }
            })
        }
    }


    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}
