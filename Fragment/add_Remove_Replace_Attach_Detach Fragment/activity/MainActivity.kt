package com.example.addremovereplacefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var addFragButton : Button? = null
    private var removeFragButton : Button? = null
    private var replaceFragButton : Button? = null
    private var replaceFrag2with1Button : Button? = null

    private var attachButton : Button? = null
    private var detachButton : Button? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragButton = findViewById(R.id.addFragButton_id)
        removeFragButton = findViewById(R.id.removeFragButton_id)
        replaceFragButton = findViewById(R.id.replaceFrag1With2Button_id)
        replaceFrag2with1Button = findViewById(R.id.replaceFrag2With1Button_id)

        attachButton = findViewById(R.id.attachFrag1_id)
        detachButton = findViewById(R.id.detachFrag1_id)

        addFragButton?.setOnClickListener {

            val f1 = FragmentA()
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.mainPlace_id,f1,"F1")
            ft.commit()
        }

        removeFragButton?.setOnClickListener {

            val fm = supportFragmentManager
            val f1 = fm.findFragmentByTag("F1") as FragmentA
            val ft = fm.beginTransaction()
            ft.remove(f1)
            ft.commit()
        }

        replaceFragButton?.setOnClickListener {

            val f2 = FragmentB()
            val fm = supportFragmentManager
            val f1 = fm.findFragmentByTag("F1") as FragmentA
            val ft = fm.beginTransaction()
            if(f1 != null)
            {
                ft.replace(R.id.mainPlace_id,f2,"F2")
                ft.commit()
            }
            else
                Toast.makeText(this,"fragment 1 is not load or delete by you",Toast.LENGTH_LONG).show()
        }
        replaceFrag2with1Button?.setOnClickListener {

            var f1 = FragmentA()
            var fm = supportFragmentManager
            var f2 = fm.findFragmentByTag("F2") as FragmentB
            val ft = fm.beginTransaction()
            ft.replace(R.id.mainPlace_id,f1,"F1")
            ft.commit()
        }

        attachButton?.setOnClickListener {

            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val f1 = fm.findFragmentByTag("F1") as FragmentA
            if(f1 != null)
            {
                ft.attach(f1)
                ft.commit()
            }
            else{
                Toast.makeText(this,"Could not find fragment 1",Toast.LENGTH_LONG).show()
            }
        }

        detachButton?.setOnClickListener {

            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val f1 = fm.findFragmentByTag("F1") as FragmentA
            if(f1 != null)
            {
                ft.detach(f1)
                ft.commit()
            }
            else
                Toast.makeText(this,"Could not find fragment 1 to detach",Toast.LENGTH_LONG).show()


        }

    }
}
