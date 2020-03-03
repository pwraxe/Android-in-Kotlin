package com.example.practice_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {


    private var drawer : DrawerLayout? = null
    private var navigation : NavigationView? = null
    private var toolbar : Toolbar? = null
    private var actionBar : ActionBarDrawerToggle? = null

    private var homeFrag = HomeFragment()
    private var securityFrag = SecurityFragment()
    private var shoppingFrag = ShoppingFragment()
    private var aboutFrag = AboutFragment()
    private var contactFrag = ContactFragment()
    private var settingFrag = SettingFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Home"
        drawer = findViewById(R.id.id_drawer)
        navigation = findViewById(R.id.id_navigation)
        toolbar = findViewById(R.id.id_toolbar)

        supportActionBar?.setHomeButtonEnabled(true)            // due to this line we can add three dot menu with navigationDrawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)       // due to also this line we can add three dot menu with navigationDrawer

        setSupportActionBar(toolbar)
        actionBar = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close)
        actionBar?.syncState()

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_area,homeFrag,"Home")      // fragment already added with FrameLayout u have to just replace that
        ft.commit()

        navigation?.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.id_home -> {
                    try {
                        val ftH = supportFragmentManager.beginTransaction()
                        ftH.replace(R.id.main_area,homeFrag,"Home")
                        ftH.addToBackStack("home")
                        ftH.commit()
                        drawer?.closeDrawer(GravityCompat.START)
                    }catch (ex : IllegalStateException){
                        Toast.makeText(this,"You already in a Home Fragment",Toast.LENGTH_LONG).show()
                        drawer?.closeDrawer(GravityCompat.START)
                    }
                }
                R.id.id_security -> {
                    val fmt = supportFragmentManager.beginTransaction()
                    fmt.replace(R.id.main_area,securityFrag,"Security")
                    fmt.addToBackStack("security")
                    fmt.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.id_shop -> {

                    val fmt = supportFragmentManager.beginTransaction()
                    fmt.replace(R.id.main_area,shoppingFrag,"Shop")
                    fmt.addToBackStack("shop")
                    fmt.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.id_about -> {
                    val fmt = supportFragmentManager.beginTransaction()
                    fmt.replace(R.id.main_area,aboutFrag,"About")
                    fmt.addToBackStack("about")
                    fmt.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.id_contact -> {
                    val fmt = supportFragmentManager.beginTransaction()
                    fmt.replace(R.id.main_area,contactFrag,"Contact")
                    fmt.addToBackStack("contact")
                    fmt.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.id_setting -> {
                    val fmt = supportFragmentManager.beginTransaction()
                    fmt.replace(R.id.main_area,settingFrag,"Setting")
                    fmt.addToBackStack("setting")
                    fmt.commit()
                    drawer?.closeDrawer(GravityCompat.START)
                }
                R.id.id_logout -> {

                    drawer?.closeDrawer(GravityCompat.START)

                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("Are you sure want to Logout?")
                    builder.setTitle("Quit?")
                    builder.setPositiveButton("Yes,Logout"){ _,_ ->
                        finish()
                    }
                    builder.setNegativeButton("No, Stay Here"){d,_ ->
                        d.dismiss()
                    }
                    builder.show()
                }
            }

            return@setNavigationItemSelectedListener false
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_extra,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> showToast("You Select ${item.title}")
            R.id.item2 -> showToast("You Select ${item.title}")
            R.id.item3 -> showToast("You Select ${item.title}")
            R.id.id_heart -> showToast("You Just Liked it")
            R.id.id_search-> showToast("No Data Available to Search")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(msg : String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

}

