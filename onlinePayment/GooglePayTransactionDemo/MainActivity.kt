package com.example.googlepaydemo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button

    val GOOGLE_PAY_PACKAGE_NAME : String = "com.google.android.apps.nbu.paisa.user";
    val GOOGLE_PAY_REQUEST_CODE : Int = 123;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.id_payButton)

        button.setOnClickListener {

            val uri = Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", "upi_id")   // pa = Payee address or business virtual payment address (VPA).
                .appendQueryParameter("pn", "personWhoReceivedPayment") // pa = Payee name
                .appendQueryParameter("mc", "merchant code")      // mc = Business retailer category code.
                .appendQueryParameter("tr", "transactionID") // tr = Transaction reference ID. (Business specific ID. Must be unique for each request.)
                .appendQueryParameter("tn", "This is just test sample payment")     // tn = transaction note (we type message when send money)
                .appendQueryParameter("am", "1")    // am = Transaction amount
                .appendQueryParameter("cu", "INR")  // cu = Currency code.
                //.appendQueryParameter("url", "your-transaction-url") // // url = Transaction reference URL.
                .build();

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME)
            startActivityForResult(intent,GOOGLE_PAY_REQUEST_CODE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_PAY_REQUEST_CODE){

            Log.e("AXE","extra : ${data?.extras}")      // extra : Bundle[mParcelledData.dataSize=632]
            Log.e("AXE","UPI : ${data?.getStringExtra("response")}")
            /**
             *  txnId=SBI02ebce9a4c2f40d9884346b7852faa32&responseCode=UP00&Status=SUCCESS&txnRef=demo_trans%409623998486
             *  i.e.
             *  txnId=SBI02ebce9a4c2f40d9884346b7852faa32 &
             *  responseCode=UP00  &
             *  Status=SUCCESS  &
             *  txnRef=demo_trans%409623998486
             *
             */

            Log.e("AXE","result Code : $resultCode")    // -1 mean Success

            Toast.makeText(this, "${data?.getStringExtra("Status")}", Toast.LENGTH_SHORT).show()
        }
    }

}