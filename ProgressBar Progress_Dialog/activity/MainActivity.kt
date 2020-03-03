package com.example.progressbar

import android.app.AlertDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//  ---------------------------------------------------------------- method 1 -> default alert dialog at run time no widgets are require

        val progress = ProgressDialog(this)
        progress.setCancelable(false)
        progress.setTitle("Downloading...")
        progress.setMessage("Content is Fetching...")
        progress.show()

        Handler().postDelayed({progress.dismiss()},6000)


//  -------------------------------------------------------------------- method 2 -> Custom Progress Bar In alert dialog


        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.layout_progress,null)

        dialog.setCancelable(false)
        dialog.setView(view)

        val ad = dialog.create()
        ad.show()

        Handler().postDelayed({ad.dismiss()},6000)




    }
}