package com.example.uploadimageonfirebase

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.uploadimageonfirebase.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    /*
    *
    *
    * Don't forget Internet Permission
    * Do needful for initialisation of firebase
    *
    * */


    private lateinit var binding : ActivityMainBinding
    private var bitmap : Uri? = null

    private lateinit var storageRef : StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        storageRef = FirebaseStorage.getInstance().reference
    }

    fun fetchImageFromLocal(view: View){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,1111)
    }

    fun saveImageOnline(view: View){

        if(bitmap != null) {
            //storageRef.child("myImages_${System.currentTimeMillis()}")
            storageRef.child("UserImages").child("myImages_${System.currentTimeMillis()}")    // ---> UserImage/myImages125421524
                .putFile(bitmap!!)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully Uploaded Image",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"Fail to Upload : ${it.message}",Toast.LENGTH_LONG).show()
                }

        }else
            Toast.makeText(this,"Please Select image first from gallery",Toast.LENGTH_LONG).show()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 1111) {
            bitmap = data?.data
            binding.idImageView.setImageURI(bitmap)
        }
    }
}