package com.example.cloudfirestoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cloudfirestoredemo.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fireDb : FirebaseFirestore
    private lateinit var collectionRef : CollectionReference

    private var lastStop : DocumentSnapshot? = null
    private var count =  0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fireDb = FirebaseFirestore.getInstance()
        collectionRef = fireDb.collection("UsersData")


        //executesBatchWrites()

        executeTransaction()
    }

    private fun executeTransaction() {

        fireDb.runTransaction {

        }

        fireDb.runTransaction { transaction ->
            val docRef = collectionRef.document("new Transaction")
            val snapshot = transaction.get(docRef)
            val view = snapshot.getLong("views")?.plus(1)
            transaction.update(docRef,"views",view)

        }
    }

    /*
    private fun executesBatchWrites() {

        val batch = fireDb.batch()

        // Add Data
        val docRef1 = collectionRef.document("external Data")
        batch.set(docRef1,UserData(1234,"aaaaaa","aaa@gmail.com"))

        //update data
        val docRef2 = collectionRef.document("externalData2")
        batch.update(docRef2,"email","pawar@gmail.com")


        //delete Data
        val docRef3 = collectionRef.document("ByoEXMp9vtvCMVcSg4sY")
        batch.delete(docRef3)
    }
*/



    override fun onStart() {
        super.onStart()

        collectionRef.addSnapshotListener(this) { querySnapshot, firebaseFirestoreException ->
            for(data in querySnapshot!!.documentChanges){

                val oldIndex =  data.oldIndex
                val newIndex = data.newIndex

                when(data.type){
                    DocumentChange.Type.ADDED -> {
                        Log.e("AXE","Added = $oldIndex : $newIndex")
                    }
                    DocumentChange.Type.MODIFIED -> {
                        Log.e("AXE","Modified = $oldIndex : $newIndex")
                    }
                    DocumentChange.Type.REMOVED -> {
                        Log.e("AXE","Removed = $oldIndex : $newIndex")
                    }
                }

            }
        }
    }


    fun saveData(view: View) {

        val name = binding.idUsername.text.toString().trim()
        val email = binding.idUserEmail.text.toString().trim()
        val views = binding.idUserViews.text.toString().toInt()


        if(name.isNotEmpty() && email.isNotEmpty() ){

            /**
             *   // following code save your data with default document id
             *  --> collectionRef.add(UserData(name,email)).addOnSuccessListener { Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show() }
             */

            // following code save your data with custom document id --> here 'user_count' is document id
            val cr = collectionRef.document("user_${++count}").set(UserData(views,name,email))
            cr.addOnSuccessListener {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }


        }else {
            binding.idUserEmail.error = "Please enter email id"
            binding.idUserEmail.requestFocus()

            binding.idUsername.error = "Please enter your name"
            binding.idUsername.requestFocus()

            binding.idUserViews.error = "Please enter view count"
            binding.idUserViews.requestFocus()
            return
        }
    }
    fun retrievedData(view: View) {

        val query : Task<QuerySnapshot> = if(lastStop == null){
            collectionRef
                .whereGreaterThan("views",1000)
                .orderBy("views")
                .limit(3)
                .get()
        }else{
            collectionRef
                .whereGreaterThan("views",1000)
                .startAfter(lastStop!!)
                .limit(3)
                .get()
        }


        var displayText = ""


//        collectionRef
//            //Query starts
//            .whereGreaterThanOrEqualTo("views",1000)    // where clause work on number type too
//            .whereLessThan("views",5000)                // we can write multiple where clause
//            .orderBy("views",Query.Direction.DESCENDING)      // we have list in reverse order
//            .limit(2)                                         // we have only first 2 data list
//            // Query Ends

//---------------------------------------------OR---------------------------------------------------


//            collectionRef
//
//                    // value need to complete otherwise we won't get data
//                .whereEqualTo("name","aaaasass")       // when i compare string like this i need index {here you got an exception with contain link which help you to create index}
//                .orderBy("views",Query.Direction.DESCENDING)
//                .get()
//                .addOnSuccessListener {

//---------------------------------------------OR---------------------------------------------------

//            collectionRef.orderBy("views")
//                .startAt(1111)  // starting point can be anything
//                .get()

                query
                    .addOnSuccessListener {

                    for (data in it.documents) {
                        Log.e("AXE", "views : ${data.get("views")}")
                        displayText += " View : ${data.get("views")} \n Name : ${data.get("name")}\n Email : ${data.get("email")}\n\n\n"
                    }

                        if(it.size() > 0) {
                            displayText+="=============================="
                            binding.idDisplayText.text = displayText   // next 3 data  just replacing
                            lastStop = it.documents[it.size() - 1]

                        }
                }

                .addOnFailureListener {
                    Log.e("AXE","Exception : ",it)
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



}