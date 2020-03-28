package com.example.popup_menu_window

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {


    private var defButton : Button? = null
    private var withIconButton : Button? = null
    private var popupWidow : Button? = null


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defButton = findViewById(R.id.id_default_popup)
        withIconButton = findViewById(R.id.id_icon_popup)
        popupWidow = findViewById(R.id.id_popup_window)

        defButton?.setOnClickListener {

            val defMenu = PopupMenu(this,defButton!!)
            defMenu.inflate(R.menu.def_menu)
            defMenu.setOnMenuItemClickListener {

                when(it.itemId){

                    R.id.id_no_battery -> showToast(it.title as String)
                    R.id.id_battery_20 -> showToast(it.title as String)
                    R.id.id_battery_30 -> showToast(it.title as String)
                    R.id.id_battery_50 -> showToast(it.title as String)
                    R.id.id_battery_60 -> showToast(it.title as String)
                    R.id.id_battery_80 -> showToast(it.title as String)
                    R.id.id_battery_90 -> showToast(it.title as String)
                    R.id.id_battery_100 -> showToast(it.title as String)
                }

                return@setOnMenuItemClickListener true

            }

            defMenu.show()
        }

        withIconButton?.setOnClickListener { it ->
            val withIcon = PopupMenu(this,it)
            withIcon.menuInflater.inflate(R.menu.icon_menu,withIcon.menu)
            withIcon.setOnMenuItemClickListener {

                when(it.itemId){
                    R.id.ic_no_battery -> showToast(it.title as String)
                    R.id.ic_battery_20 -> showToast(it.title as String)
                    R.id.ic_battery_30 -> showToast(it.title as String)
                    R.id.ic_battery_50 -> showToast(it.title as String)
                    R.id.ic_battery_60 -> showToast(it.title as String)
                    R.id.ic_battery_80 -> showToast(it.title as String)
                    R.id.ic_battery_90 -> showToast(it.title as String)
                    R.id.ic_battery_100 -> showToast(it.title as String)
                }
                return@setOnMenuItemClickListener false
            }

            try{
                val _popup = PopupMenu::class.java.getDeclaredField("mPopup")
                _popup.isAccessible = true
                val _menu = _popup.get(withIcon)
                _menu.javaClass
                    .getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                    .invoke(_menu,true)

            }catch (e : Exception){
                e.printStackTrace()
            }finally {
                withIcon.show()
            }



        }

        popupWidow?.setOnClickListener {

            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.custom_layout,null,false)
            window.contentView = view
            window.showAsDropDown(popupWidow)
            window.update()
            view.findViewById<CardView>(R.id.id_popup_card).setOnClickListener {
                window.dismiss()
            }
        }
    }
    private fun showToast(msg : String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}
