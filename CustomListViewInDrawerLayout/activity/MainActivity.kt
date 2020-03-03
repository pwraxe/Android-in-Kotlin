package com.example.customlist_navdrawer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity(), DrawerLayout.DrawerListener {
    override fun onDrawerStateChanged(newState: Int) {

    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerClosed(drawerView: View) {

    }

    override fun onDrawerOpened(drawerView: View) {
    }

    private var listView : ListView? = null
    private var drawer : DrawerLayout? = null
    private var toolbar : Toolbar? = null
    private var actionBar : ActionBarDrawerToggle? = null

    private var B30 = Battery30()
    private var B60 = Battery60()
    private var B90 = Battery90()
    private var B100 = Battery100()

    var currentFrag : Int = R.id.main_place

    var battery = arrayOf("Battery 30%","Battery 60%","Battery 90%","Battery 100%")
    var batteryImage = arrayOf(R.drawable.ic_battery30,R.drawable.ic_battery60,
        R.drawable.ic_battery90,R.drawable.ic_battery100)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Home Activity"

        drawer = findViewById(R.id.drawer_id)
        toolbar = findViewById(R.id.toolBar_id)
        setSupportActionBar(toolbar)
        actionBar = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close)
        actionBar?.syncState()


        listView = findViewById(R.id.listView_id)
        val customAdapter = CustomListViewNavDrawer(this,battery,batteryImage)
        listView?.adapter = customAdapter



        listView?.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this,"${parent.getItemAtPosition(position)}",Toast.LENGTH_SHORT).show()
            when(position) {
                0 -> {
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place,B30,"B30")
                    ft.addToBackStack("+B30")
                    drawer?.closeDrawer(GravityCompat.START)
                    title = parent.getItemAtPosition(position).toString()
                    ft.commit()

                }
                1 -> {
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place,B60,"B60")
                    ft.addToBackStack("+B60")
                    title = parent.getItemAtPosition(position).toString()
                    drawer?.closeDrawer(GravityCompat.START)
                    ft.commit()
                }
                2 -> {
                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place,B90,"B90")
                    ft.addToBackStack("+B90")
                    title = parent.getItemAtPosition(position).toString()
                    drawer?.closeDrawer(GravityCompat.START)
                    ft.commit()
                }
                3 -> {

                    val fm = supportFragmentManager
                    val ft = fm.beginTransaction()
                    ft.replace(R.id.main_place,B100,"B100")
                    ft.addToBackStack("+B100")
                    title = parent.getItemAtPosition(position).toString()
                    drawer?.closeDrawer(GravityCompat.START)
                    ft.commit()
                }
            }
        }
    }
}

class CustomListViewNavDrawer(var context: Context,var battery : Array<String>, var batteryImage : Array<Int>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.sample_layout,null)

        val BatteryName = view.findViewById<TextView>(R.id.batteryPercentage_id)
        val BatteryIcon = view.findViewById<ImageView>(R.id.battery_icon_id)

        BatteryName.text = battery[position]
        BatteryIcon.setImageResource(batteryImage[position])
        return view


    }

    override fun getItem(position: Int): Any  = battery[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = battery.size
}
