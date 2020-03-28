package com.example.popup_menu_in_listitem

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView

class CustomRecycler(
    private var context: Context,
    private var name: ArrayList<String>,
    private var binding: View?
) : RecyclerView.Adapter<CustomRecycler.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val _name = itemView.findViewById<TextView>(R.id.id_name)
        val _menu = itemView.findViewById<ImageView>(R.id.id_three_dot_menu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout,null,false))

    override fun getItemCount(): Int = name.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder._name?.text = name[position]

        holder._menu?.setOnClickListener {

            val myMenu = PopupMenu(context,holder._menu)
            myMenu.inflate(R.menu.edit_delete_menu)
            myMenu.setOnMenuItemClickListener {
                when(it.itemId){

                    R.id.id_edit -> {
                        val dialog = Dialog(context)
                        val view = dialog.layoutInflater.inflate(R.layout.custom_dialog,null,false)
                        dialog.setContentView(view)

                        view.findViewById<Button>(R.id.id_updateButton)?.setOnClickListener {

                            val et_name = view.findViewById<EditText>(R.id.id_new_name)
                            val newName = et_name.text.toString().trim()
                            if(TextUtils.isEmpty(newName)){
                                et_name?.error = "New Name Required"
                                et_name?.requestFocus()
                                return@setOnClickListener
                            }
                            name[position] = newName
                            notifyDataSetChanged()
                            notifyItemChanged(position)
                            dialog.dismiss()

                        }

                        dialog.show()
                    }
                    R.id.id_delete -> {
                        name.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position,name.size)

                        if(name.size == 0){
                            binding?.findViewById<RecyclerView>(R.id.id_recyclerView)?.visibility = View.GONE
                            binding?.findViewById<TextView>(R.id.id_error_msg)?.visibility = View.VISIBLE
                        }
                    }
                }
                return@setOnMenuItemClickListener true
            }
            myMenu.show()

        }

    }
}