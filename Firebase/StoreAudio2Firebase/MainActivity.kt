package com.example.uploadimageonfirebase

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.uploadimageonfirebase.databinding.ActivityMainBinding
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    /*
    *
    * Don't forget Internet Permission
    * Do needful for initialisation of firebase
    *
    * */


    private lateinit var binding : ActivityMainBinding

    private lateinit var storageRef : StorageReference

    private var audio : Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        storageRef = FirebaseStorage.getInstance().reference
    }

    fun fetchAudioFromLocal(view: View){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*"
        startActivityForResult(intent,1111)
    }

    fun saveAudioOnline(view: View){

        binding.idProgress.visibility = View.VISIBLE

        if(audio != null) {
            storageRef.child("UserAudios")
                .child("video_${System.currentTimeMillis()}")    // ---> UserImage/myImages125421524
                .putFile(audio!!)
                .addOnSuccessListener {
                    binding.idProgress.visibility = View.GONE
                    Toast.makeText(this, "Successfully Uploaded Audio", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    binding.idProgress.visibility = View.GONE
                    Toast.makeText(this, "Fail to Upload : ${it.message}", Toast.LENGTH_LONG).show()
                }
        }
        else
            Toast.makeText(this,"Please Select image first from gallery",Toast.LENGTH_LONG).show()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == 1111) {
            audio = data?.data
            val player = MediaPlayer.create(this,audio)
            player.start()
        }
    }
}