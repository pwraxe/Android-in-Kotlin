package com.example.appbarlayoutdemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.LinearLayout
import android.widget.QuickContactBadge
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private var fromMail : QuickContactBadge? = null
    private var fromNumber : QuickContactBadge? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Quick Contact Badge"
        fromMail  = findViewById(R.id.id_fromMail)
        fromNumber = findViewById(R.id.id_fromNumber)

        fromMail?.assignContactFromEmail("xyzabc@gmail.com",true)
        fromMail?.setMode(ContactsContract.QuickContact.MODE_LARGE)
        fromMail?.setPrioritizedMimeType(ContactsContract.PRIMARY_ACCOUNT_TYPE)


        fromNumber?.assignContactFromPhone("1234567890",true)
        fromNumber?.setMode(ContactsContract.QuickContact.MODE_MEDIUM)
        fromNumber?.setPrioritizedMimeType(ContactsContract.PRIMARY_ACCOUNT_TYPE)


    }
}
