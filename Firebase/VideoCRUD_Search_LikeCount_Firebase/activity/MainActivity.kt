package com.example.mxplayerdemo

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mxplayerdemo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var videoUri : Uri? = null

    private lateinit var fireStorage: StorageReference
    private lateinit var fireRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        fireStorage = FirebaseStorage.getInstance().getReference("UsersStorage")
        fireRef = FirebaseDatabase.getInstance().getReference("UsersDatabase")

    }

    fun loadLocalVideo(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        startActivityForResult(intent,1111)
    }
    fun saveLocalVideoToServer(view: View) {

        val videoName = binding.idInputEditText.text.toString().trim()
        if(videoName.isNotEmpty() && videoUri != null){

            binding.idProgressBar.visibility = View.VISIBLE
            fireStorage.child(videoName).putFile(videoUri!!).addOnCompleteListener{ task ->

                val byteSize = task.result?.metadata?.sizeBytes
                val toKb=  byteSize?.div(1000.toDouble())
                val toMb =  toKb?.div(1000)?.times(100)!!.roundToInt().div(100.0)
                val toGb =  toMb.div(1000).times(100).roundToInt().div(100.0)

                Log.e("ASP","Size in Byte : $byteSize")         // 18786989 Byte
                Log.e("ASP","Size in KB   : $toKb")             // 18786.989 KB
                Log.e("ASP","Size in MB   : $toMb")             // 18.79 MB
                Log.e("ASP","Size in GB   : $toGb")             // 0.02 GB

                task.result?.metadata?.reference?.downloadUrl?.addOnSuccessListener {uri ->

                    fireRef.push().setValue(UsersVideoData(videoName,uri.toString(),toMb,0))
                        .addOnSuccessListener {
                            binding.idProgressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(applicationContext, "Fail to Saved", Toast.LENGTH_SHORT).show()
                            Log.e("AXE","Error to Save : $it")
                        }
                }
            }

        }else{
            Toast.makeText(this, "Video & its name required", Toast.LENGTH_SHORT).show()
            binding.idLoadLocalVideo.setBackgroundColor(Color.RED)
            binding.idLoadLocalVideo.requestFocus()
            return
        }
    }

    fun viewAllVideos(view: View) { startActivity(Intent(this,ViewAllVideos::class.java))}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1111 && resultCode == Activity.RESULT_OK){
            videoUri = data?.data
            binding.idVideoView.setVideoURI(videoUri)
            binding.idVideoView.start()
        }
    }

}