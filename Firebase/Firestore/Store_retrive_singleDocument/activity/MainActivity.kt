package com.example.cloudfirestoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cloudfirestoredemo.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


/**
 * don't forget INTERNET-PERMISSION (for safety)
 * check rules if you unable to retrieved data in firestore
 * */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fireStore : FirebaseFirestore

    private lateinit var docRef : DocumentReference

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        fireStore = FirebaseFirestore.getInstance()

    }

    fun saveData(view: View) {

        val name = binding.idUsername.text.toString().trim()
        val email = binding.idUserEmail.text.toString().trim()

        if(name.isNotEmpty() && email.isNotEmpty()){

            fireStore.collection("UserData").document("user_${++count}")
                .set(UserData(name,email))
                .addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { Toast.makeText(this, "exception : $it", Toast.LENGTH_SHORT).show() }
        }else {
            binding.idUserEmail.error = "Please enter email id"
            binding.idUserEmail.requestFocus()

            binding.idUsername.error = "Please enter your name"
            binding.idUsername.requestFocus()

            return
        }


    }
    fun retrievedData(view: View) {

        //fetching single document
        fireStore.collection("UserData").document("user_1")
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if(documentSnapshot?.exists()!!){

                    // Now, I have 4 different method to retrieved single document data

                    //Method-1
                    Log.e("AXE","[\"name\"] : ${documentSnapshot["name"]}")         // ["name"] : Alex doe
                    Log.e("AXE","[\"email\"] : ${documentSnapshot["email"]}")       // ["email"] : alex@gmail.com

                    //Method-2
                    Log.e("AXE","data.get(\"name\") : ${documentSnapshot.data?.get("name")}")   // data.get("name") : Alex doe
                    Log.e("AXE","data.get(\"email\") : ${documentSnapshot.data?.get("email")}") // data.get("email") : alex@gmail.com

                    //Method-3
                    Log.e("AXE",".get(\"name\")  : ${documentSnapshot.get("name")}")        // .get("name")  : Alex doe
                    Log.e("AXE",".get(\"email\")  : ${documentSnapshot.get("email")}")      // .get("email")  : alex@gmail.com

                    //Method-4
                    Log.e("AXE","toObj name : ${documentSnapshot.toObject(UserData::class.java)?.name}")        // toObj name : Alex doe
                    Log.e("AXE","toObj email : ${documentSnapshot.toObject(UserData::class.java)?.email}")      // toObj email : alex@gmail.com

                    Log.e("AXE","metadata : ${documentSnapshot.metadata}")      // metadata : SnapshotMetadata{hasPendingWrites=false, isFromCache=true}
                    Log.e("AXE","id : ${documentSnapshot.id}")                  // id : user_1

                }else{
                    Log.e("AXE","Exception : ",firebaseFirestoreException)
                }
            }
    }
    
    // fetch all document at a time 
     fireStore.collection("UserData").addSnapshotListener { querySnapshot, firebaseFirestoreException ->

            for(data in querySnapshot!!.documents){
                
                Log.e("AXE","${count}: ${data.data}   :  ${data.id}")           //Ex.o/p ==>  {name=Alex doe, email=alex@gmail.com}   :  user_1
            }

        }
    
    
    
}
