package com.codexdroid.easyupi_payment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.codexdroid.easyupi_payment.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val GOOGLE_PAY_PACKAGE_NAME : String = "com.google.android.apps.nbu.paisa.user";
    val GOOGLE_PAY_REQUEST_CODE : Int = 123;

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    fun easyPay(view : View){

        // ruchita upi ID : ruchita091200@okicici

        val uri = Uri.parse("upi://pay").buildUpon()

            .appendQueryParameter("pa", "vicky.pawar198-2@okhdfcbank")   // pa = Payee address or business virtual payment address (VPA).
            .appendQueryParameter("pn", "Akshay Pawar") // pa = Payee name
            //.appendQueryParameter("mc", "FCIWGQTs4TAPrr")      // mc = Business retailer category code.
           .appendQueryParameter("tr", getTransactionReferenceID()) // tr = Transaction reference ID. (Business specific ID. Must be unique for each request.)
            .appendQueryParameter("tn", "This is just test sample payment")     // tn = transaction note (we type message when send money)
            .appendQueryParameter("am", "1")    // am = Transaction amount
            .appendQueryParameter("cu", "INR")  // cu = Currency code.
            //.appendQueryParameter("url", "your-transaction-url") // // url = Transaction reference URL.
            .build();

//
//
//        val uri2 = Uri.parse("upi://pay").buildUpon()
//            .appendQueryParameter("pa","ruchita091200@okicici")
//            .appendQueryParameter("pn","Ruchita Badgujar")
//            .appendQueryParameter("tr",getTransactionReferenceID())
//            .appendQueryParameter("tn","This is Second Payment")
//            .appendQueryParameter("am","1")
//            .appendQueryParameter("cu","INR")
//            .build()


        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri

        //intent.setPackage(GOOGLE_PAY_PACKAGE_NAME)

        val chooser = Intent.createChooser(intent,"Make Payment with: ")
        if(chooser.resolveActivity(packageManager) != null){
            try{
                startActivityForResult(chooser,GOOGLE_PAY_REQUEST_CODE)
            }catch (ane : ActivityNotFoundException){
                Toast.makeText(this, "Google Play is Not installed, Install it first then try again ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_PAY_REQUEST_CODE ){

            binding.idExtra.text = data?.extras.toString()
            binding.idRespoce.text = data?.getStringExtra("response")
            /**
             *  txnId=SBI02ebce9a4c2f40d9884346b7852faa32&responseCode=UP00&Status=SUCCESS&txnRef=demo_trans%409623998486
             *  i.e.
             *  txnId=SBI02ebce9a4c2f40d9884346b7852faa32 &
             *  responseCode=UP00  &
             *  Status=SUCCESS  &
             *  txnRef=demo_trans%409623998486
             *
             */

            if(data?.getStringExtra("Status") == "Success"){
                Toast.makeText(this, data.getStringExtra("Status"), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "${data?.getStringExtra("Status")} in else branch", Toast.LENGTH_SHORT).show()
            }

            Log.e("AXE","result Code : $resultCode")    // -1 mean Success
        }
    }

    private fun getTransactionReferenceID () : String{

        var mergeStr : String = ""
        val randomChar = ArrayList<Char>()
        for(i in 0..4){
            randomChar.add(Random.nextInt(65,90).toChar())
            randomChar.add(Random.nextInt(97,122).toChar())
            randomChar.add(Random.nextInt(48,57).toChar())
        }
        randomChar.shuffle()
        //convert array to toString
        for(index in 0 until randomChar.size){
            mergeStr+="${randomChar[index]}"
        }
        return mergeStr
    }
}
