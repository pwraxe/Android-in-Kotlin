package com.example.axebank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import de.hdodenhof.circleimageview.CircleImageView

class HomePage : AppCompatActivity() {

    private var customerSelfieImage : CircleImageView? = null
    private var customerFullName : TextView? = null
    private var customerBankBalence : TextView? = null

    private var sendCard : CardView? = null
    private var loanCard : CardView? = null
    private var aboutCard : CardView? = null
    private var contactCard : CardView? = null

    private var toolBar : Toolbar? = null

    val helper = DatabaseHelper(this)
    private var customerID : Int = 0
    private var FullName : String? = ""



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val intent = intent
        customerID = intent.getIntExtra("ID",0)

        customerSelfieImage = findViewById(R.id.setUserSelfiePic_id)
        customerFullName = findViewById(R.id.customerfullName_id)
        customerBankBalence = findViewById(R.id.bankBalence_id)

        sendCard = findViewById(R.id.sendMoneyCard_id)
        loanCard = findViewById(R.id.loanCard_id)
        aboutCard = findViewById(R.id.aboutCard_id)
        contactCard = findViewById(R.id.contactCard_id)

        toolBar = findViewById(R.id.homeToolbar_id)
        setSupportActionBar(toolBar)


        sendCard?.setOnClickListener {
            val intent = Intent(this,SendMoney::class.java)
            intent.putExtra("C_ID",customerID)
            startActivity(intent)
        }
        //var convertToResource = helper.getImageFromCustomerID(customerID)
        customerSelfieImage?.setImageResource(R.drawable.user_icon)
        //Toast.makeText(this,"$convertToResource",Toast.LENGTH_LONG).show()

        loanCard?.setOnClickListener {
            startActivity(Intent(this,Loan::class.java))
        }

        aboutCard?.setOnClickListener {
            startActivity(Intent(this,About::class.java))
        }
        contactCard?.setOnClickListener {
            startActivity(Intent(this,Contact::class.java))
        }

        refreshHomePage()

    }

    override fun onResume() {
        super.onResume()
        refreshHomePage()
        customerFullName?.text = FullName
        customerBankBalence?.text = "₹ ${BankBalence.bankBalence}"
    }

    private fun refreshHomePage()
    {
        var _id  = 0

        val cursor = helper.getFullName(customerID)
        while(cursor.moveToNext())
        {
            if (cursor.count == 0)
                Toast.makeText(this,"No Rows Available",Toast.LENGTH_LONG).show()
            else
            {
                _id = cursor.getInt(0)
                val fname = cursor.getString(1)
                val lname = cursor.getString(2)
                if(customerID == _id)
                {
                    FullName = "$fname $lname"
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.history_id ->{
                val intent = Intent(this,History::class.java)
                intent.putExtra("CID",customerID)
                startActivity(intent)
            }
            R.id.profile_id -> {
                val intent = Intent(this,Profile::class.java)
                intent.putExtra("CID",customerID)
                startActivity(intent)
            }
            R.id.update_id -> {
                val intent = Intent(this,Update::class.java)
                intent.putExtra("CID",customerID)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Quit")
        builder.setMessage("Are You Sure, You want to Quit?")
        builder.setPositiveButton("Yes"){_,_ ->
            super.onBackPressed()
        }
        builder.setNegativeButton("No"){dialog,_ ->
            dialog.cancel()
        }
        var dialog = builder.create()
        dialog.show()
    }


}
