package com.example.roomdb_practice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb_practice.databinding.ActivityMainBinding
import com.example.roomdb_practice.room_stuffs.UsersData

class MainActivity : AppCompatActivity(), RoomUpdateDeleteOperation {

    private lateinit var binding : ActivityMainBinding

    private lateinit var mainViewModel : MainViewModel

    private lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.usersAllData.observe(this, Observer { dataList ->

            if(dataList.isEmpty()){
                binding.idLottieFiles.visibility = View.VISIBLE
                binding.idRecyclerView.visibility = View.GONE
            }else{
                binding.idLottieFiles.visibility = View.GONE
                binding.idRecyclerView.visibility = View.VISIBLE

                adapter = CustomRecyclerAdapter(this,dataList)
                binding.idRecyclerView.adapter = adapter
            }

        })



    }

    fun addNewData(view: View) {
        startActivityForResult(Intent(this,AddNewData::class.java),1111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1111 && resultCode == Activity.RESULT_OK){
            val id = data?.getIntExtra("E_ID",0)
            val name = data?.getStringExtra("E_NAME")
            val email = data?.getStringExtra("E_EMAIL")
            val mobile = data?.getLongExtra("E_MOBILE",0L)

            val usersData = UsersData(id!!, name.toString(), email.toString(), mobile!!)

            mainViewModel.insertData(usersData)
            Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()
        }

        if(requestCode == 2222 && resultCode == Activity.RESULT_OK){
            val id = data?.getIntExtra("E_ID",0)
            val name = data?.getStringExtra("E_NAME")
            val email = data?.getStringExtra("E_EMAIL")
            val mobile = data?.getLongExtra("E_MOBILE",0L)

            val usersData = UsersData(id!!, name.toString(), email.toString(), mobile!!)

            mainViewModel.editData(usersData)
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun editData(usersData : UsersData){

        val intent = Intent(this,AddNewData::class.java)
        intent.putExtra("U_ID",usersData.u_id)
        intent.putExtra("U_NAME",usersData.name)
        intent.putExtra("U_EMAIL",usersData.email)
        intent.putExtra("U_MOBILE",usersData.mobile)
        intent.putExtra("isUpdate",true)

        startActivityForResult( intent, 2222 )

    }

    override fun deleteThis(usersData: UsersData){
        mainViewModel.deleteThis(usersData)
        Toast.makeText(this, "See You soon ${usersData.name}", Toast.LENGTH_SHORT).show()
    }

}