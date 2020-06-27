package com.example.storeretrive_imagesfromfirebase

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storeretrive_imagesfromfirebase.databinding.CustomDesignBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import org.jetbrains.annotations.NotNull

class CustomDataAdapter (@NotNull var option : FirebaseRecyclerOptions<UsersLocalData>) :
    FirebaseRecyclerAdapter<UsersLocalData,CustomDataAdapter.ViewHolder>(option) {

    class ViewHolder(var binding : CustomDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(CustomDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int, userData : UsersLocalData) {

        holder.binding.idNameText.text = userData.userName
        holder.binding.idRawEmailText.text = userData.userEmail
        holder.binding.idRawMobileText.text = userData.userMobile

        Glide.with(holder.binding.idRawImageView.context).load(userData.imageUrl)
                .placeholder(R.drawable.ic_load_img)
                .error(R.drawable.ic_sad_face)
            .into(holder.binding.idRawImageView)

        holder.binding.idRawCrossIcon.setOnClickListener {


            // first delete database data & then delete storage image
            getRef(position).child("imageUrl").removeValue()
                .addOnSuccessListener {
                    Toast.makeText(holder.binding.idRawCrossIcon.context,"Image deleted ",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(holder.binding.idRawCrossIcon.context,"image url not exist in db",Toast.LENGTH_SHORT).show()
                }

            //now delete storage image
            FirebaseStorage.getInstance().getReference(userData.userMobile.toString())
                .child(userData.imageName.toString())
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(holder.binding.idRawCrossIcon.context,"storage image also deleted",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    holder.binding.idRawImageView.setImageResource(R.drawable.ic_sad_face)
                    Toast.makeText(holder.binding.idRawCrossIcon.context,"fail to delete image",Toast.LENGTH_SHORT).show()
                }
        }

        holder.binding.idRawDeleteIcon.setOnClickListener {
            getRef(position).removeValue()
        }


    }


}