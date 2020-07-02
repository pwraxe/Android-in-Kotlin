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
import com.google.firebase.firestore.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fireDb : FirebaseFirestore
    private lateinit var collectionRef : CollectionReference

    private var count =  0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fireDb = FirebaseFirestore.getInstance()
        collectionRef = fireDb.collection("UsersData")

    }

    override fun onStart() {
        super.onStart()

        // when onStart method calls then data updates
        var displayText = ""

        collectionRef.addSnapshotListener(this) { queryDocSnap, firebaseFirestoreException ->
            for (data in queryDocSnap!!.documents){
                displayText += " ID : ${data.id} \n Name : ${data["name"]} \n Email : ${data["email"]} \n\n\n"
            }
            binding.idDisplayText.text = displayText
        }
    }

    fun saveData(view: View) {

        val name = binding.idUsername.text.toString().trim()
        val email = binding.idUserEmail.text.toString().trim()


        if(name.isNotEmpty() && email.isNotEmpty()){

            /**
             *   // following code save your data with default document id
             *  collectionRef.add(UserData(name,email)).addOnSuccessListener { Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show() }
             */

            // following code save your data with custom document id --> here 'user_count' is document id
            collectionRef.document("user_${++count}").set(UserData(name,email)).addOnSuccessListener {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }

        }else {
            binding.idUserEmail.error = "Please enter email id"
            binding.idUserEmail.requestFocus()

            binding.idUsername.error = "Please enter your name"
            binding.idUsername.requestFocus()

            return
        }
    }
    fun retrievedData(view: View) {

        var displayText = ""
        collectionRef.get().addOnSuccessListener {
            for (data in it.documents){
                displayText += " ID : ${data.id} \n Name : ${data.get("name")}\n Email : ${data.get("email")}\n\n\n"
            }
            binding.idDisplayText.text = displayText
        }

    }

    fun updateData(view: View) {
        val name = binding.idUsername.text.toString().trim()
        val email = binding.idUserEmail.text.toString().trim()

        if(name.isNotEmpty() && email.isNotEmpty()){

            collectionRef.document("user_1").update("name",name).addOnSuccessListener {
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
            }


        }else{
            binding.idUserEmail.error = "Please enter email id"
            binding.idUserEmail.requestFocus()

            binding.idUsername.error = "Please enter your name"
            binding.idUsername.requestFocus()

            return
        }


    }

    fun deleteEmail(view: View) {
        collectionRef.document("user_1")
            .update("email",FieldValue.delete())
            .addOnSuccessListener {
                Toast.makeText(this, "email deleted", Toast.LENGTH_SHORT).show()
            }
    }

    fun deleteDocument(view: View) {
        collectionRef.document("user_1").delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Document Deleted", Toast.LENGTH_SHORT).show()
            }
    }

}