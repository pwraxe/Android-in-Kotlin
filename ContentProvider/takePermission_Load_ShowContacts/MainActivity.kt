package com.example.addcontact2contactapp

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.*
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteException
import android.provider.ContactsContract
import android.system.StructUtsname
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private var listView : ListView? = null
    private var permission  = arrayOf(android.Manifest.permission.READ_CONTACTS,android.Manifest.permission.WRITE_CONTACTS)

    private var contacts : ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.id_ListView)
        takePermission()



    }

    private fun takePermission() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ){

            if(ContextCompat.checkSelfPermission(this,permission.toString()) == PackageManager.PERMISSION_GRANTED){
                LoadContacts().execute()
            }else {

                if(ActivityCompat.shouldShowRequestPermissionRationale(this,permission.toString())){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Why We need?")
                    builder.setMessage("The Permission need for accessing your contact list")
                    builder.setPositiveButton("Ok"){_,_ ->
                        ActivityCompat.requestPermissions(this,permission,1526)
                    }
                    builder.show()

                }else {
                    ActivityCompat.requestPermissions(this,permission,1526)
                }

            }


        } else {
            Toast.makeText(this,"Android OS Version is lower than Nougat",Toast.LENGTH_LONG).show()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1526){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Thanks for sharing contact list",Toast.LENGTH_LONG).show()
                LoadContacts().execute()
            }else{
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun getContactsList() : ArrayList<String> {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER)
        val selection = null
        val selectionArgs = null
        val sortOrder = null

        val resolver = contentResolver.query(uri,projection,selection, selectionArgs, sortOrder)
        while (resolver!!.moveToNext()){
            val name = resolver.getString(resolver.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = resolver.getString(resolver.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


            contacts.add("\t\t\t $name \n\t\t\t $number")
        }

        return contacts

    }

    inner class LoadContacts : AsyncTask<Void,Void,Void>() {

        private var contactsList : MutableList<String> = ArrayList()
        lateinit var process : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            process = ProgressDialog(this@MainActivity)
            process.setMessage("Loading Contacts")
            process.setCancelable(false)
            process.show()

        }
        override fun doInBackground(vararg params: Void?): Void? {

            contactsList = getContactsList()
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            listView?.adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,contactsList)
            process.dismiss()
        }

    }





}

