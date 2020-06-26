package com.example.store_retrieved_data_firebase

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.store_retrieved_data_firebase.databinding.CustomRecyclerDesignBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import org.jetbrains.annotations.NotNull


class CustomRecyclerAdapter(@NotNull options: FirebaseRecyclerOptions<UsersCommonData>) : FirebaseRecyclerAdapter<UsersCommonData,CustomRecyclerAdapter.ViewHolder>(options)

{
    class ViewHolder(var binding : CustomRecyclerDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomRecyclerDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, usersData: UsersCommonData) {
        holder.binding.idRawFullName.text = usersData.name
        holder.binding.idRawEmail.text = usersData.email
        holder.binding.idRawMobile.text = usersData.mobile.toString()

        val address ="${usersData.usersAddData.street}, ${usersData.usersAddData.area}, " +
                "${ usersData.usersAddData.town}, ${usersData.usersAddData.city}, " +
                "${usersData.usersAddData.state}, ${usersData.usersAddData.country}"

        holder.binding.idRawAddress.text = address

        holder.binding.idRawDeleteIcon.setOnClickListener {

            FirebaseDatabase.getInstance().getReference("Users")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {}

                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (data in snapshot.children){

                            if(data.key == usersData.mobile.toString()){
                                data.ref.removeValue()
                                    .addOnCompleteListener {
                                        Toast.makeText(holder.binding.idRawDeleteIcon.context,"Deleted",Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    }
                })
        }
    }
}
