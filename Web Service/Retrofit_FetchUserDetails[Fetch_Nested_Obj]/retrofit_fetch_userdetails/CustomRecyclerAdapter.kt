package com.example.retrofit_fetch_userdetails

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter ( private var context: Context, private var usersData : ArrayList<UsersData> ) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val custom_id = itemView.findViewById<TextView>(R.id.id_custom_id)
        val custom_name = itemView.findViewById<TextView>(R.id.id_custom_name)
        val custom_username = itemView.findViewById<TextView>(R.id.id_custom_username)
        val custom_email = itemView.findViewById<TextView>(R.id.id_custom_email)
        val custom_phone_number = itemView.findViewById<TextView>(R.id.id_custom_phone)
        val custom_website = itemView.findViewById<TextView>(R.id.id_custom_website)
        val custom_address = itemView.findViewById<TextView>(R.id.id_custom_address)
        val custom_company = itemView.findViewById<TextView>(R.id.id_custom_company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = usersData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.custom_id.text = "ID : ${usersData[position].user_id}"
        holder.custom_name?.text = "Name : ${usersData[position].user_name}"
        holder.custom_username?.text = "Username : ${usersData[position].user_username}"
        holder.custom_email?.text = "Email : ${usersData[position].user_email}"
        holder.custom_phone_number?.text = "Phone Number : ${usersData[position].user_phone}"
        holder.custom_website?.text = "Website : ${usersData[position].user_website}"

        holder.custom_address?.setOnClickListener {

            val dialog = AlertDialog.Builder(it.rootView.context)
            dialog.setTitle("User Address Details")
            dialog.setMessage("Street : ${usersData[position].user_address?.addr_street} \n" +
                    "Suite : ${usersData[position].user_address?.addr_suite} \n" +
                    "City : ${usersData[position].user_address?.addr_city} \n" +
                    "Zipcode : ${usersData[position].user_address?.addr_zip} \n " +
                    "Location : (${usersData[position].user_address?.addr_geo?.geo_loc_lat}, ${usersData[position].user_address?.addr_geo?.geo_loc_lang})")

            dialog.setPositiveButton("OK") { d, _ ->
                d.dismiss()
            }

            dialog.show()
        }

        holder.custom_company?.setOnClickListener {
            val dialog = AlertDialog.Builder(it.rootView.context)
            dialog.setTitle("User Company Details")
            dialog.setMessage("Name : ${usersData[position].user_company?.comp_name} \n" +
                    "catchPhrase : ${usersData[position].user_company?.comp_catchPharse} \n" +
                    "BS : ${usersData[position].user_company?.comp_bs} \n")
            dialog.setPositiveButton("OK"){d,_->
                d.dismiss()
            }
            dialog.show()
        }
    }


}