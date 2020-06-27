package com.example.storeretrive_imagesfromfirebase

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.storeretrive_imagesfromfirebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


/**
* //firebase recycler adapter
*   implementation 'com.firebaseui:firebase-ui-database:6.2.1'
 *
 *   don't forgot internet permission
*/




class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var uriImages : Uri

    private lateinit var dbRef : DatabaseReference

    private lateinit var storageRef : StorageReference

    var imageCount = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        dbRef = FirebaseDatabase.getInstance().getReference("UsersData")

        storageRef = FirebaseStorage.getInstance().reference      // 'Cake Images' is folder name

    }

    fun selectLocalImage(view: View) {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent,1526)

    }

    fun uploadLocalData(view: View) {


        if(!::uriImages.isInitialized){  // if(::uriImages.isInitialized)

            Toast.makeText(this,"Please Select Image",Toast.LENGTH_SHORT).show()
            binding.idSelectImageButton.setBackgroundColor(Color.RED)
            return
        }else{

            val name = binding.idName.text.toString().trim()
            val email = binding.idEmail.text.toString().trim()
            val mobileNo = binding.idMobileNo.text.toString()

            if(name.isEmpty() && email.isEmpty()){
                binding.idName.error = "Name Required"
                binding.idEmail.error = "Email Required"
                binding.idMobileNo.error = "Mobile No. Required"
                return
            }else{

                binding.idProgressBar.visibility = View.VISIBLE
                binding.imageView.visibility = View.GONE
                // upload image
                val imageName = "cakePic_${++imageCount}"
                storageRef.child("$mobileNo/$imageName").putFile(uriImages)
                    // cake_pic is image name in firebase
                    // if you did not push then multiple image replace with same name
                    // Cake Images is folder name ::> if exists then upload init else create new folder & upload image

                    .addOnSuccessListener {
                        binding.idProgressBar.visibility = View.GONE
                        binding.imageView.visibility = View.VISIBLE

                        it.metadata?.reference?.downloadUrl?.addOnSuccessListener { uri ->

                            val usersData = UsersLocalData(name,email,mobileNo,uri.toString(),imageName)

                            dbRef.child("$mobileNo").setValue(usersData,imageCount)
                                .addOnCompleteListener {
                                    Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show()
                                }
                        }
                }
            }
        }
    }


    fun viewFirebaseData(view: View) { startActivity(Intent(this,ViewImages::class.java)) }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode ==1526 && resultCode == Activity.RESULT_OK){
            uriImages = data?.data!!
            binding.imageView.setImageURI(uriImages)

        }
    }


}