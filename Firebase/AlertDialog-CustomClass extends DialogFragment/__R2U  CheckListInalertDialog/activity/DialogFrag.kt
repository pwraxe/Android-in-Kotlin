package com.example.dialogfragmentdemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogFrag : DialogFragment() {

    var daysList = ArrayList<String>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("My Custom Dialog")

        builder.setMultiChoiceItems(R.array.days,null){dialog,which,isCheck ->

            var res = resources.getStringArray(R.array.days)
            daysList.add(res[which])

        }

        // builder.setMessage("Do you like this dialog??")
//        builder.setItems(R.array.days){dialog,which ->            // No need of +ve and -ve buttons here
//
//            val res = resources.getStringArray(R.array.days)
//            Toast.makeText(activity?.applicationContext,"Day : ${res[which]}",Toast.LENGTH_LONG).show()
//            dialog.dismiss()
//
//        }
        builder.setPositiveButton("Yes"){dialog,_ ->
            Toast.makeText(activity?.applicationContext,"$daysList",Toast.LENGTH_LONG).show()
            daysList.removeAll(daysList)
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel"){dialog,_->

            dialog.dismiss()
        }

        return builder.create()

    }


    }

