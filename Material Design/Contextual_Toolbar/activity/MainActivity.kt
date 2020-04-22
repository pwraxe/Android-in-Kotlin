package com.example.materialdesign_part1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.id_changeButton)

        val toolbar = findViewById<Toolbar>(R.id.id_toolbar)
        toolbar?.title = "Maharashtra"
        toolbar?.subtitle = "Mumbai"

        toolbar?.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_arrow_back)
        toolbar?.inflateMenu(R.menu.menu_popup)



        button?.setOnClickListener {

            var actionBar = this@MainActivity.startActionMode(ContextualCallBack())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                actionBar?.onWindowFocusChanged(true)
            }
            actionBar?.tag = "-TAG-"
            actionBar?.invalidate()



        }
    }
    inner class ContextualCallBack : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return true
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_onclick,menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.title = "changedView"
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {

        }
    }
}
