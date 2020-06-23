package com.example.storereadimagefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.storereadimagefirebase.databinding.ActivityDownloadImageBinding
import com.example.storereadimagefirebase.databinding.DesignLayoutBinding
import com.google.firebase.database.*

class DownloadImageActivity : AppCompatActivity() {


    private lateinit var binding : ActivityDownloadImageBinding

    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference

    private var dataList = ArrayList<ImageInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_download_image)

        db = FirebaseDatabase.getInstance("https://storeloadimagefirebase.firebaseio.com/")
        ref = db.getReference("UsersData")

        binding.idRecyclerView.visibility = View.GONE
        binding.idProgressBar.visibility = View.VISIBLE
        loadData()


    }

    private fun loadData() {

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Error : $error",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for(data in snapshot.children){

                    val imageUrl = data.child("imageUrl").getValue(String::class.java)
                    val imageName = data.child("imageName").getValue(String::class.java)

                   dataList.add(ImageInfo(imageName!!,imageUrl!!))

                }
                binding.idRecyclerView.visibility = View.VISIBLE
                binding.idProgressBar.visibility = View.GONE
                binding.idRecyclerView.adapter = CustomImageAdapter(dataList)
            }
        })
    }
}

class CustomImageAdapter(var imageInfo : ArrayList<ImageInfo>) : RecyclerView.Adapter<CustomImageAdapter.ViewHolder>(){


    class ViewHolder(var binding : DesignLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(DesignLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount(): Int = imageInfo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.binding.idRawImageView.context)
            .load(imageInfo[position].imageUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_download)
                .error(R.drawable.ic_upload))
            .into(holder.binding.idRawImageView)

    }


}
























