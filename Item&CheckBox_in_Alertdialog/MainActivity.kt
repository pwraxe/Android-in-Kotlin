package com.example.dialogfragmentdemo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var itemButton : Button? = null
    private var checkboxButton : Button? = null

    private var daysArr = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemButton = findViewById(R.id.itemButton)
        checkboxButton = findViewById(R.id.checkboxButton)

        itemButton?.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Items")
            builder.setItems(R.array.days){d,w->
                val res = resources.getStringArray(R.array.days)
                Toast.makeText(this,"Day : ${res[w]}",Toast.LENGTH_LONG).show()
                d.dismiss()
            }
            builder.show()
        }

        checkboxButton?.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Items")
            builder.setMultiChoiceItems(R.array.days,null){dialog,which,isCheck ->


                var res = resources.getStringArray(R.array.days)
                daysArr.add(res[which])


                //Toast.makeText(this,"Day : $which : $isCheck",Toast.LENGTH_LONG).show()

            }
            builder.setPositiveButton("Select Item"){d,w ->

                Toast.makeText(this,"$daysArr",Toast.LENGTH_LONG).show()
                daysArr.removeAll(daysArr)



            }
            builder.show()

        }

    }
}