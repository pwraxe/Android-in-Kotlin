package com.example.databaseoperation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    val helper = DatabaseHelper(this)
    private var toolbar: androidx.appcompat.widget.Toolbar? = null
    private var textView : TextView? = null
    private var listView : ListView? = null
    var usersData = ArrayList<String>()

    var adapt : ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        toolbar = findViewById(R.id.id_toolBar)
        setSupportActionBar(toolbar)
        listView = findViewById(R.id.id_listView)
        adapt = usersData?.let { ArrayAdapter(this,android.R.layout.simple_list_item_1, it) }
        listView?.adapter = adapt
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.db_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.id_add_user -> {
                startActivity(Intent(this,UserDataActivity::class.java))
            }
            R.id.id_update -> {
                getIDFromUser(1,"Update")
            }
            R.id.id_delete -> {
                getIDFromUser(2,"Delete")

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        usersData . removeAll(usersData)
        loadData()
        adapt?.notifyDataSetChanged()

    }


    private fun loadData() {
        val cursor = helper.selectAllData()
        if (cursor?.count == 0)
            textView?.text = "Database is Empty, Add data by clicking plus button above"

        //usersData = ArrayList()
        while(cursor!!.moveToNext()){

            val collectData = "Customer ID \t:  ${cursor.getInt(0)} \n " +
                    "First Name  \t:  ${cursor.getString(1)} \n " +
                    "Last Name   \t:  ${cursor.getString(2)} \n" +
                    "Email ID    \t:  ${cursor.getString(3)} \n" +
                    "Mobile No   \t:  ${cursor.getLong(4)} \n" +
                    "Education   \t:  ${cursor.getString(5)} \n\n"

            usersData.add(collectData)
        }


    }


    private fun getIDFromUser(num : Int,buttonText : String)
    {

        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.layout_get_id,null)
        builder.setView(view)
        builder.setPositiveButton(buttonText){_,_->}

        val dialog = builder.create()
        dialog.show()
        val userID = view.findViewById<EditText>(R.id.id_get_user_id)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val uid = userID?.text.toString().toInt()
            dialog.dismiss()
            if(num == 1)
                updateData(uid)
            if(num == 2) {
                deleteData(uid)
            }
        }
    }

    private fun updateData(id : Int) {
        var isIDAvailable = false
        val cursor = helper.getUserID()
        if(cursor.count == 0)
            Toast.makeText(this,"Database is Empty",Toast.LENGTH_LONG).show()
        else{
            while (cursor.moveToNext())
            {
                val user_id = cursor.getInt(0)
                if(id == user_id)
                {
                    isIDAvailable = true
                    val intent = Intent(this,UpdateActivity::class.java)
                    intent.putExtra("_ID",id)
                    startActivity(intent)
                }
            }
            if(!isIDAvailable)
                Toast.makeText(this,"Id is not  available",Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteData(id : Int) {

        val r = helper.deleteData(id)
        if(r == -1)
            Toast.makeText(this,"Error to Delete Data",Toast.LENGTH_LONG).show()
        else {
            Toast.makeText(this, "Data Deleted Successfully", Toast.LENGTH_LONG).show()
            onResume()
        }
    }
}
