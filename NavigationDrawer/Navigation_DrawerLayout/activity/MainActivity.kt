package com.example.drawerlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){


    private var drawer : DrawerLayout? = null
    private var toolbar : Toolbar? = null
    private var navigationView : NavigationView? = null
    private var actionBar : ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer = findViewById(R.id.drawer_layout_id)
        toolbar = findViewById(R.id.toolBar)
        navigationView = findViewById(R.id.navigation_view)
        setSupportActionBar(toolbar)

        actionBar = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close)
        drawer?.addDrawerListener(actionBar!!)
        //actionBar?.isDrawerIndicatorEnabled = true
        actionBar?.syncState()

        navigationView?.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.Frag1_id -> {
                    val frag1 = FirstFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag1, "F1")
                    ft.addToBackStack("+F1")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.Frag2_id -> {
                    val frag2 = SecondFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag2, "F2")
                    ft.addToBackStack("+F2")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.Frag3_id -> {
                    val frag3 = ThirdFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag3, "F3")
                    ft.addToBackStack("+F3")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.Frag4_id -> {
                    val frag4 = FourFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag4, "F4")
                    ft.addToBackStack("+F4")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.Frag5_id -> {
                    val frag5 = FifthFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag5, "F5")
                    ft.addToBackStack("+F5")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.Frag6_id -> {
                    val frag6 = SixFragment()
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place, frag6, "F6")
                    ft.addToBackStack("+F6")
                    ft.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
            }
            return@setNavigationItemSelectedListener false

        }
    }
}

