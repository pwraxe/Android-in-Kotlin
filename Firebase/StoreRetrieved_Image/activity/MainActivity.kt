package com.example.storereadimagefirebase

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.example.storereadimagefirebase.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var imageUri : Uri? = null

    private lateinit var storageRef : StorageReference
    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference

    private lateinit var imageName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        db = FirebaseDatabase.getInstance()
        ref = db.getReference("UsersData")
        storageRef = FirebaseStorage.getInstance().reference.child("UserPic")
    }

    fun imageFromLocalStorage(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,1234)
    }

    fun uploadImage(view: View){
        storeImage()
    }

    private fun storeImage(){

        binding.idProgressBar.visibility = View.VISIBLE

        if(imageUri != null){

            imageName = "userImg_${System.currentTimeMillis()}.${getFileExtension()}"

             storageRef.child(imageName)
                .putFile(imageUri!!)
                .addOnSuccessListener {

                    // get image url and store in real database

                    it.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->
                        val info = ImageInfo(imageName,uri.toString())
                        ref.push().setValue(info)
                            .addOnSuccessListener {
                                binding.idProgressBar.visibility = View.GONE
                                Toast.makeText(this,"Url also saved",Toast.LENGTH_LONG).show()
                            }
                    }
                }
        }else{
            Toast.makeText(this,"Please Choose Image",Toast.LENGTH_LONG).show()
        }
    }

    fun downloadImage(view: View){
        startActivity(Intent(this,DownloadImageActivity::class.java))
    }

    private fun getFileExtension() : String? {
        val cr = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime?.getExtensionFromMimeType(cr.getType(imageUri!!))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1234 && resultCode == Activity.RESULT_OK){
            imageUri = data?.data
            binding.idImage.setImageURI(imageUri)

        }
    }

}


data class ImageInfo(var imageName : String, var imageUrl : String)