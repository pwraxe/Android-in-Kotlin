package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navController : NavController
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        drawerLayout = binding.idDrawer

        navController = this.findNavController(R.id.id_NavHostFragment)

        NavigationUI.setupWithNavController(binding.idNavigationView,navController)

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.id_NavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }




}

/*

* Note to Remember:
* ------------------
*
* drawer_menu.xml  &  navigation.xml
*
* both fragment have same id's
*
* Ex.
* navigation.xml > fragment id > homeFragment   &
* drawer_menu > item id > homeFragment
*
* if these are same then & only then you can navigate from drawer menu to specific folder
*
*
* */