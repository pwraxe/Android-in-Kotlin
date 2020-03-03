package com.example.axebank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import com.dpizarro.pinview.library.PinView
import de.hdodenhof.circleimageview.CircleImageView

class MPIN : AppCompatActivity() {

    private val helper = DatabaseHelper(this)
    private var customerID : Int = 0

    private var userM_Pin : PinView? = null
    private var tickButton : CircleImageView? = null
    private var forgotMpin : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpin)
        var intent = intent
        customerID = intent.getIntExtra("ID",0)

        userM_Pin = findViewById(R.id.pinView_id)
        tickButton = findViewById(R.id.makeTransactionButton_id)
        forgotMpin = findViewById(R.id.forgotMpin_id)

        tickButton?.setOnClickListener {
            var pin = userM_Pin?.pinResults?.toInt()

            var rowPin : Int = 0
            var cid : Int = 0
            val cursor = helper.getMpin()
            while(cursor.moveToNext())
            {
                if(cursor.count == 0)
                {
                    Toast.makeText(this,"No Rows Available",Toast.LENGTH_LONG).show()
                }
                else
                {
                    cid = cursor.getInt(0)
                    rowPin = cursor.getInt(1)
                    if(cid == customerID && rowPin == pin)
                    {
                        Toast.makeText(this,"Money has been send successfully",Toast.LENGTH_LONG).show()
                        this.finish()
                    }
                    else
                        Toast.makeText(this,"Invalid M-Pin Entered",Toast.LENGTH_LONG).show()
                }
            }

        }


        forgotMpin?.setOnClickListener {

            var id : Int = 0
            var mpin : Int = 0


            val cursor = helper.getMpin(customerID)
            if (cursor.count == 0)
                Toast.makeText(this,"", Toast.LENGTH_LONG).show()
            else
            {
                while (cursor.moveToNext())
                {
                    id = cursor.getInt(0)
                    mpin = cursor.getInt(1)
                    if(id == customerID)
                    {
                        var showPass = AlertDialog.Builder(this)
                        showPass.setTitle("Password Recovery Process... ")
                        showPass.setMessage("Your Mpin is : $mpin")

                        showPass.setPositiveButton("OK"){dialog,_ ->
                            dialog.cancel()
                        }
                        showPass.show()
                    }
                }
            }

        }


    }
}

