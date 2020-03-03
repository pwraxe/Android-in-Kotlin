package com.example.testingapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private var listView : ListView? = null
    lateinit var context : Context
    private var contactList : MutableList<String> = ArrayList()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        listView = findViewById(R.id.listView_id)
        ContactLoader().execute()
        listView?.setOnItemClickListener { parent, _, position, _ ->
            Toast.makeText(this,"${parent.getItemAtPosition(position)}",Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUserContactList() : MutableList<String> {
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
        return contactList
    }


    inner class ContactLoader : AsyncTask<Void,Void,Void>() {

        lateinit var progress : ProgressDialog
        lateinit var contact_list : MutableList<String>
        override fun onPreExecute() {
            super.onPreExecute()
            progress = ProgressDialog(context)
            progress.setMessage("Loading Contact...")
            progress.setCancelable(false)
            progress.show()
        }
        @RequiresApi(Build.VERSION_CODES.O)
        override fun doInBackground(vararg params: Void?): Void? {
            contact_list = getUserContactList()
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progress.dismiss()
            listView?.adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1,contact_list)
        }
    }


}


