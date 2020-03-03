package com.example.axebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SendMoney : AppCompatActivity() {


    var time : String? = null
    var date : String? = null
    val helper = DatabaseHelper(this)

    private var customerID : Int = 0

    private var chooseBank : Spinner? = null
    private var ifscCode : EditText? = null
    private var firstAccoutNumber : EditText? = null
    private var secondAccountNumber : EditText? = null
    private var sendCustomMoney : EditText? = null
    private var senderMessage : EditText? = null
    private var sendButton : CardView? = null


    private var selectedBank : String? = null
    private var ifsc_code : String? = null
    private var accountNumber : Long = 0
    private var confirmAccNo : Long = 0
    private var userAmount : Int = 0
    private var messageOnSendMoney : String? = null
     private var alertEmoji : Int = 0x26A0 //&#x26A0;	&#9888

    var allBankNames = arrayOf("Select Bank","ALLAHABAD BANK","ANDHRA BANK", "BANK OF BARODA", "BANK OF INDIA", "BANK OF MAHARASHTRA", "CANARA BANK", "CENTRAL BANK OF INDIA", "CORPORATION BANK",
        "DENA BANK", "IDBI BANK LTD.", "IND BANK HOUSING LTD.", "INDBANK MERCHANT BANKING SERVICES LTD.", "INDIAN BANK", "INDIAN OVERSEAS BANK",
        "JAMMU & KASHMIR BANK LTD.,THE", "ORIENTAL BANK OF COMMERCE", "PNB GILTS LTD.", "PUNJAB & SIND BANK","PUNJAB NATIONAL BANK",
        "STATE BANK OF INDIA", "SYNDICATE BANK", "UCO BANK", "UNION BANK OF INDIA", "UNITED BANK OF INDIA", "VIJAYA BANK")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)

        val intent = intent
        customerID = intent.getIntExtra("C_ID",0)
        chooseBank = findViewById(R.id.sendOnBankSpinner_id)
        ifscCode = findViewById(R.id.sendOnIFSC_Code_id)
        firstAccoutNumber = findViewById(R.id.sendOnfirstAccNo_id)
        secondAccountNumber = findViewById(R.id.sendOnConfirmAccNo_id)
        senderMessage = findViewById(R.id.sendOnMsg_id)
        sendCustomMoney = findViewById(R.id.sendCustomMoney_id)
        sendButton = findViewById(R.id.sendOnClick_id)





        val defaultAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,allBankNames)
        chooseBank?.adapter = defaultAdapter

        sendButton?.setOnClickListener {
            selectedBank = chooseBank?.selectedItem.toString()
            ifsc_code = ifscCode?.text.toString()
            accountNumber = firstAccoutNumber?.text.toString().toLong()
            confirmAccNo = secondAccountNumber?.text.toString().toLong()
            userAmount = sendCustomMoney?.text.toString().toInt()
            messageOnSendMoney = senderMessage?.text.toString()



            if(ifsc_code?.length != 11 ){
                ifscCode?.error = "Invalid IFSC Code"
                return@setOnClickListener
            }else if(accountNumber != confirmAccNo) {
                firstAccoutNumber?.error = "${getAlertEmoji(alertEmoji)} Account Number Not Match"
                secondAccountNumber?.error = "${getAlertEmoji(alertEmoji)} Account Number Not Match"
                return@setOnClickListener
            }else if (userAmount.equals("") && userAmount > 500000 || userAmount < 50){
                sendCustomMoney?.error = "You can send 50 to 500000 INR"
                return@setOnClickListener
            }else if(messageOnSendMoney.equals("")){
                senderMessage?.error = "Reason for sending Money"
                return@setOnClickListener
            }
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Wait...!")
            builder.setMessage("You are sending $userAmount to $accountNumber, Bank is not Responsible to retrieve your amount")
            builder.setPositiveButton("Yes I am Sure"){_,_ ->

                var date = getSystemDate()
                var time : String = getSystemTime()

                var l = helper.sendMoneyHistory(customerID.toString(),selectedBank,ifsc_code,accountNumber.toString(),userAmount.toString(),messageOnSendMoney,"$date $time")
//                if(l == true)
//                    Toast.makeText(this,"send money data inserted",Toast.LENGTH_LONG).show()
//                else
//                    Toast.makeText(this,"Error send money data inserted",Toast.LENGTH_LONG).show()


                val intent = Intent(this,MPIN::class.java)
                intent.putExtra("ID",customerID)
                startActivity(intent)
                BankBalence.bankBalence = BankBalence.bankBalence - userAmount


                this.finish()
            }
            builder.setNegativeButton("No, Let Me Checked"){dialog,_ ->
                dialog.cancel()
            }
            builder.show()


        }

    }
    private fun getSystemDate() : String
    {
        val current = Calendar.getInstance()
        return DateFormat.getDateInstance(DateFormat.FULL).format(current.time)
    }

    private fun getSystemTime() : String
    {
        val current = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("hh:mm:ss a")
        return simpleDateFormat.format(current.time).toString()
    }

    private fun getAlertEmoji(emoji : Int) : String = String(Character.toChars(emoji))
}
