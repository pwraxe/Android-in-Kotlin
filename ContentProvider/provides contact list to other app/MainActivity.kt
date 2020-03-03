package com.example.testingapp

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import org.jetbrains.annotations.Contract

class MainActivity : AppCompatActivity() {

    private var listView : ListView? = null
    var contactList : MutableList<String> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView_id)
        getUserContactList()
        listView?.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList)



    }


    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUserContactList() {

        val resolver = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null)
        if(resolver != null && resolver.moveToFirst())
        {
            do {
                val id = resolver.getString(resolver.getColumnIndex(ContactsContract.Contacts._ID))
                val hasNumber = resolver.getString(resolver.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    .toInt()
                if (hasNumber > 0) {
                    val subResolver = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                        arrayOf(id), null
                    )

                    if (subResolver != null) {
                        while (subResolver.moveToNext()) {
                            val name = resolver.getString(resolver.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                            val number = subResolver.getString(subResolver.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            //Log.e("AXE", "$name --> $number")
                            contactList.add("$name -->  $number")
                        }
                    }
                    subResolver?.close()
                }
            }while (resolver.moveToNext())
        }

        resolver?.close()


    }
}
