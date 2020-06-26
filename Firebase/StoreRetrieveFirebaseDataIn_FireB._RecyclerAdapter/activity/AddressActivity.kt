package com.example.store_retrieved_data_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.store_retrieved_data_firebase.databinding.ActivityAddressBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val EXTRA_NAME = "ExtraName"
private const val EXTRA_EMAIL = "ExtraEmail"
private const val EXTRA_MOBILE = "ExtraMobile"

class AddressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddressBinding

    private lateinit var name : String
    private lateinit var email : String
    private var mobile : Long? = null


    private lateinit var db : FirebaseDatabase
    private lateinit var ref : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_address)

        db = FirebaseDatabase.getInstance()
        ref = db.getReference("Users")

        val intent = intent
        name = intent.getStringExtra(EXTRA_NAME)!!
        email = intent.getStringExtra(EXTRA_EMAIL)!!
        mobile = intent.getLongExtra(EXTRA_MOBILE,404)

        // we have data here of previous activity

    }

    fun collectAddressData(view: View) {

        val street = binding.idStreet.text.toString().trim()
        val area = binding.idArea.text.toString().trim()
        val town = binding.idTown.text.toString().trim()
        val city = binding.idCity.text.toString().trim()
        val state = binding.idState.text.toString().trim()
        val country = binding.idCountry.text.toString().trim()


        val addressData = UsersAddressData(street,area,town,city,state,country)
        val userData = UsersCommonData(name,email,mobile,addressData)

        ref.child(mobile.toString()).setValue(userData)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(this,MainActivity::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext,"Fail to Saved : $it",Toast.LENGTH_LONG).show()
            }

    }
}