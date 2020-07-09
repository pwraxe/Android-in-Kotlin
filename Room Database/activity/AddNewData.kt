package com.example.roomdb_practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.roomdb_practice.databinding.ActivityAddNewDataBinding
import java.lang.NumberFormatException

class AddNewData : AppCompatActivity() {

    private lateinit var binding : ActivityAddNewDataBinding

    private var id = 0
    private lateinit var name : String
    private lateinit var email : String
    private var mobile : Long = 0

    private var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_data)

        isUpdate = intent.getBooleanExtra("isUpdate",false)

        if(isUpdate){

            id = intent.getIntExtra("U_ID",0)
            name = intent.getStringExtra("U_NAME")!!
            email = intent.getStringExtra("U_EMAIL")!!
            mobile = intent.getLongExtra("U_MOBILE",0L)


            binding.idNewID.setText("$id")
            binding.idNewID.isEnabled = false

            binding.idNewName.setText(name)
            binding.idNewEmail.setText(email)
            binding.idNewMobile.setText("$mobile")
        }


    }

    fun collectAndSaveData(view: View) {


        try {
            id = binding.idNewID.text.toString().toInt()
        }catch (nfe : NumberFormatException){
            binding.idNewID.error = "Please Enter ID"
            binding.idNewID.requestFocus()
            return
        }

        name = binding.idNewName.text.toString().trim()
        email = binding.idNewEmail.text.toString()

        try {
            mobile = binding.idNewMobile.text.toString().toLong()
        }catch (nfe : NumberFormatException){
            binding.idNewMobile.error = "Mobile Number Required"
            binding.idNewMobile.requestFocus()
            return
        }

        if(name.isNotEmpty() && email.isNotEmpty()){
            val intent = Intent()
            intent.putExtra("E_ID",id)
            intent.putExtra("E_NAME",name)
            intent.putExtra("E_EMAIL",email)
            intent.putExtra("E_MOBILE",mobile)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }else{
            binding.idNewName.error = "Name Required"
            binding.idNewName.requestFocus()

            binding.idNewEmail.error = "Email Required"
            binding.idNewEmail.requestFocus()

            return
        }
    }

}