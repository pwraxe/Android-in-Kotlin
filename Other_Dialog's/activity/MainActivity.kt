package com.example.otherdialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var progressDialog : Button? = null
    private var horiProgDialog : Button? = null
    private var progressBar : Button? = null
    private var progressIcon : ProgressBar? = null
    private var CustomProgressBar : Button? = null
    private var datePicker : Button? = null
    private var displayDate : TextView? = null
    private var timePicker : Button? = null
    private var displayTime : TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = findViewById(R.id.id_button1)
        horiProgDialog = findViewById(R.id.id_button2)
        progressBar = findViewById(R.id.id_button3)
        progressIcon = findViewById(R.id.id_progressBar)
        CustomProgressBar = findViewById(R.id.id_button4)
        datePicker = findViewById(R.id.id_button5)
        displayDate = findViewById(R.id.id_displayDate)
        displayTime= findViewById(R.id.id_displayTime)
        timePicker = findViewById(R.id.id_button6)

        progressDialog?.setOnClickListener {

            val progress = ProgressDialog(this)
            progress.setTitle("Progress Title")
            progress.setMessage("Progress Message... (Deprecated)")
            progress.setCancelable(true)
            progress.show()
        }

        horiProgDialog?.setOnClickListener {

            var progVal = 0
            val progress = ProgressDialog(this)
            progress.setTitle("Progress Title")
            progress.setMessage("Horizontal Progress Message... (Deprecated)")
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progress.show()


            for (i in 0..100) {
                try {
                    ++progVal
                    progress.progress = progVal
                    //Thread.sleep(100)
                } catch (ie: InterruptedException) { }
            }
        }


        progressBar?.setOnClickListener {
            progressIcon?.visibility = View.VISIBLE
            Handler().postDelayed({
                progressIcon?.visibility = View.GONE
            },5000)
            Toast.makeText(this,"Progress auto invisible after 5 Second",Toast.LENGTH_LONG).show()
        }

        CustomProgressBar?.setOnClickListener {

            val dialog = Dialog(this)
            val view = layoutInflater.inflate(R.layout.custom_progress,null,false)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            Handler().postDelayed({
                dialog.dismiss()

            },5000)
            Toast.makeText(this,"Diaog will dismiss after 5 second",Toast.LENGTH_LONG).show()
            dialog.show()



        }

        datePicker?.setOnClickListener {
/*
            val YYYY = Calendar.YEAR
            val MM = Calendar.MONTH
            val DD = Calendar.DAY_OF_MONTH
*/
            val datePick = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    displayDate?.text = "$dayOfMonth/${month+1}/$year"

                },1996,6,10)
            datePick.show()

        }

        timePicker?.setOnClickListener {

            val pickTime = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                if(hourOfDay < 12){
                    displayTime?.text = "$hourOfDay : $minute AM"
                }else{
                    displayTime?.text = "$hourOfDay : $minute PM"
                }



            },Calendar.HOUR,Calendar.MINUTE,true)
            pickTime.show()

        }



    }
}
