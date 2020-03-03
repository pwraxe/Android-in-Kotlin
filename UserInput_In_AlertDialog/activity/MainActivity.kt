package com.example.inflater

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

class MainActivity : AppCompatActivity() {

    var dialogButton: Button? = null
    var FirstName: EditText? = null
    var LastName: EditText? = null
    var FullName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogButton = findViewById(R.id.getDialogButton_id)
        FullName = findViewById(R.id.rowText_id)


        dialogButton?.setOnClickListener {


            val dialog = AlertDialog.Builder(this)
            //var view = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view = layoutInflater.inflate(R.layout.users_data,null)

            FirstName = view.findViewById(R.id.firstName_id)
            FirstName = EditText(applicationContext)
            FirstName?.hint = "First Name -->"
            FirstName?.setPadding(20,20,20,20)


            LastName = view.findViewById(R.id.lastName_id)
            LastName = EditText(applicationContext)
            LastName?.hint = "Last Name -->"
            LastName?.setPadding(20,20,20,20)
            dialog.setTitle("User")

            var ll = LinearLayout(this)
            ll.orientation = LinearLayout.VERTICAL
            ll.addView(FirstName)
            ll.addView(LastName)
            dialog.setView(ll)


            dialog.setPositiveButton("Submit") { _, _ ->
                var fName = FirstName?.text.toString()
                var lName = LastName?.text.toString()
                FullName?.text = "$fName $lName"
            }
            dialog.setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            dialog.show()
        }

    }
}
