package com.example.retrofit_viewmodel_databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // back button visible but won't work
        val navController = findNavController(R.id.id_NavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

     //your own back button, for work
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.id_NavHostFragment)
        return navController.navigateUp()

    }



}