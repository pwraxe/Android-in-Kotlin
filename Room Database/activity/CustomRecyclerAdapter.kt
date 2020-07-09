package com.example.roomdb_practice

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_practice.databinding.CustomRecycleDesignBinding
import com.example.roomdb_practice.room_stuffs.UsersData

class CustomRecyclerAdapter(private var context: Context,var userData : List<UsersData>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    private lateinit var roomUD : RoomUpdateDeleteOperation

    class ViewHolder(var binding: CustomRecycleDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(CustomRecycleDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun getItemCount(): Int = userData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.e("AXE","bind holder : ${userData[position].u_id}  : ${userData[position].name}  :  ${userData[position].email}  :  ${userData[position].mobile}")
        holder.binding.idRawTextView.text =
                " ID : ${userData[position].u_id} \n" +
                " Name : ${userData[position].name} \n" +
                " Email : ${userData[position].email} \n " +
                " Mobile : ${userData[position].mobile} \n"


        holder.binding.idRawEdit.setOnClickListener {
            roomUD = context as RoomUpdateDeleteOperation
            roomUD.editData(userData[position])
        }

        holder.binding.idRawDelete.setOnClickListener {
            roomUD = context as RoomUpdateDeleteOperation
            roomUD.deleteThis(userData[position])
        }
    }
}


interface RoomUpdateDeleteOperation{

    fun editData(usersData: UsersData)

    fun deleteThis(usersData: UsersData)

}