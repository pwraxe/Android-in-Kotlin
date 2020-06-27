package com.example.storeretrive_imagesfromfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.storeretrive_imagesfromfirebase.databinding.ActivityViewImagesBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class ViewImages : AppCompatActivity() {

    private lateinit var binding : ActivityViewImagesBinding

    private lateinit var dbRef : DatabaseReference

    private lateinit var adapter : CustomDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_images)

        binding.idRecyclerView.visibility = View.GONE
        binding.idProgressBar.visibility = View.VISIBLE

        // this is important to load text data >> reference should be given
        dbRef = FirebaseDatabase.getInstance().getReference("UsersData")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    loadData()
                }else{
                    binding.idSadFaceIcon.visibility = View.VISIBLE
                    binding.idRecyclerView.visibility = View.GONE
                    binding.idProgressBar.visibility = View.GONE
                }
            }
        })


        val option = FirebaseRecyclerOptions.Builder<UsersLocalData>()
            .setQuery(dbRef,UsersLocalData::class.java)
            .build()
        adapter = CustomDataAdapter(option)

        // I am always forgot to attach this adapter to recycler view
        binding.idRecyclerView.adapter = adapter
    }

    private fun loadData(){

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                binding.idSadFaceIcon.visibility = View.VISIBLE
                binding.idRecyclerView.visibility = View.GONE
                binding.idProgressBar.visibility = View.GONE
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (data in snapshot.children){
                    binding.idSadFaceIcon.visibility = View.GONE
                    binding.idProgressBar.visibility = View.GONE
                    binding.idRecyclerView.visibility = View.VISIBLE

                    Toast.makeText(applicationContext,"Data Loaded",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}